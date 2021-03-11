import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { Groups } from 'src/app/models/groups';
import { EdgeService } from 'src/app/services/edge.service';

@Component({
  selector: 'app-group-card',
  templateUrl: './group-card.component.html',
  styleUrls: ['./group-card.component.css']
})
export class GroupCardComponent implements OnInit {

  @Input() group!: Groups;

  groupNameUpperCase: string = '';

  constructor(
    public edgeService: EdgeService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.groupNameUpperCase = this.group.name.toUpperCase();
  }

  openGroup():void {
    this.edgeService.selectedGroup = this.group.id;
    this.router.navigate(['/group-sites',  this.group.id]);
  }
}
