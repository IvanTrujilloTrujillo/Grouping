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
    }
  }

  getGroupsByUser(): void {
    this.edgeService.getGroupsByUser().subscribe(result => {
      this.app.groupList = result;
    });
  }

  openNewGroupDialog(): void {
    const dialogRef = this.newGroupDialog.open(NewGroupComponent);

    dialogRef.afterClosed().subscribe(result => {
      setInterval(() => {this.ngOnInit()}, 2000);
    });
  }

}
