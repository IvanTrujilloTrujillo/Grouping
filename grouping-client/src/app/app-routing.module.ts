import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CommentsComponent } from './components/comments/comments.component';
import { GroupsComponent } from './components/groups/groups.component';
import { HomeComponent } from './components/home/home.component';
import { NewReviewComponent } from './components/new-review/new-review.component';
import { NewSiteComponent } from './components/new-site/new-site.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'groups', component: GroupsComponent },
  { path: 'comments', component: CommentsComponent },
  { path: 'new-site', component: NewSiteComponent },
  { path: 'new-review', component: NewReviewComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
