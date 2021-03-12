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
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      this.getGroupsByUser();
      this.edgeService.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  getGroupsByUser(): void {
    this.edgeService.getGroupsByUser().subscribe(result => {
      this.groupWithMembersList = result;
      this.groupWithMembersList.shift();
      this.edgeService.groupList = [];
      this.groupWithMembersList.forEach(element => {
        this.edgeService.groupList.push(new Groups(element.id, element.name, element.groupAdmin, ''));
      });
    });
  }

  openNewGroupDialog(): void {
    const dialogRef = this.newGroupDialog.open(NewGroupComponent, {
      hasBackdrop: true,
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      setTimeout(() => {this.ngOnInit()}, 100);
    });
  }

  openJoinGroupDialog(): void {
    const dialogRef = this.newGroupDialog.open(JoinGroupComponent, {
      hasBackdrop: true,
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      setTimeout(() => {this.ngOnInit()}, 100);
    });
  }

}
