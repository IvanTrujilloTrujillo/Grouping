import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { GroupWithMembers } from 'src/app/models/group-with-members';
import { Groups } from 'src/app/models/groups';
import { EdgeService } from 'src/app/services/edge.service';
import { JoinGroupComponent } from '../join-group/join-group.component';
import { NewGroupComponent } from '../new-group/new-group.component';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit {

  groupWithMembersList: GroupWithMembers[] = [];

  constructor(
    public edgeService: EdgeService,
    private router: Router,
    private newGroupDialog: MatDialog
  ) {}

  ngOnInit(): void {
    //Check if there is a tocken in local storage, if not, redirect to login page
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {

      //Get the groups of an user
      this.getGroupsByUser();
      //Get the user id from the tocken
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  //Get the groups of an user
  getGroupsByUser(): void {
    this.edgeService.getGroupsByUser().subscribe(result => {
      this.groupWithMembersList = result;
      //console.log(this.groupWithMembersList);

      //Get the index of the Global group in the list
      const globalIndex = this.groupWithMembersList.findIndex(element => element.id === 1);

      //Remove the Global group from the list
      this.groupWithMembersList.splice(globalIndex, 1);

      //Reset the group list
      this.edgeService.groupList = [];
      this.groupWithMembersList.forEach(element => {

        //Fill the group list with the groups, but without the number of members
        this.edgeService.groupList.push(new Groups(element.id, element.name, element.groupAdmin, ''));
      });
    });
  }

  //Function to open the new group dialog to create it
  openNewGroupDialog(): void {
    const dialogRef = this.newGroupDialog.open(NewGroupComponent, {
      hasBackdrop: true,
      disableClose: true
    });

    //After close, reload the group list to show the new one
    dialogRef.afterClosed().subscribe(result => {
      setTimeout(() => {this.ngOnInit()}, 1000);
    });
  }

  //Function to open the join group dialog to join an existing group
  openJoinGroupDialog(): void {
    const dialogRef = this.newGroupDialog.open(JoinGroupComponent, {
      hasBackdrop: true,
      disableClose: true
    });

    //After close, reload the group list to show the new one
    dialogRef.afterClosed().subscribe(result => {
      setTimeout(() => {this.ngOnInit()}, 1000);
    });
  }

}
