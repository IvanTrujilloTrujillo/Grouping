import { Component, Input, OnInit } from '@angular/core';
import { Groups } from 'src/app/models/groups';

@Component({
  selector: 'app-group-card',
  templateUrl: './group-card.component.html',
  styleUrls: ['./group-card.component.css']
})
export class GroupCardComponent implements OnInit {

  @Input() group!: Groups;

  constructor() { }

  ngOnInit(): void {
  }

}
