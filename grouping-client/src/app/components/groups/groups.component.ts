import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { EdgeService } from 'src/app/services/edge.service';
import { NewGroupComponent } from '../new-group/new-group.component';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit {

  constructor(
    public app: AppComponent,
    private edgeService: EdgeService,
    private router: Router,
    private newGroupDialog: MatDialog
  ) { }

  ngOnInit(): void {
    if(this.edgeService.tocken === null || this.edgeService.tocken === '') {
      this.router.navigate(['/login']);
    } else {
      this.getGroupsByUser();
      this.app.userId = Number(this.edgeService.tocken.substr(0, 4));
    }
  }

  getGroupsByUser(): void {
    this.edgeService.getGroupsByUser().subscribe(result => {
      this.app.groupList = result;
      this.app.groupList.shift();
    });
  }

  openNewGroupDialog(): void {
    const dialogRef = this.newGroupDialog.open(NewGroupComponent);

    dialogRef.afterClosed().subscribe(result => {
      setTimeout(() => {this.ngOnInit()}, 100);
    });
  }

  openJoinGroupDialog(): void {
    /*const dialogRef = this.newGroupDialog.open(JoinGroupComponent);

    dialogRef.afterClosed().subscribe(result => {
      setTimeout(() => {this.ngOnInit()}, 100);
    });*/
  }

}
