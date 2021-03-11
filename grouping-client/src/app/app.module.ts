import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { HomeComponent } from './components/home/home.component';
import { GroupsComponent } from './components/groups/groups.component';
import { NewReviewComponent } from './components/new-review/new-review.component';
import { SiteCardComponent } from './components/home/site-card/site-card.component';
import { CommentsComponent } from './components/comments/comments.component';
import { NewSiteComponent } from './components/new-site/new-site.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule, MAT_DIALOG_DEFAULT_OPTIONS } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NewGroupComponent } from './components/new-group/new-group.component';
import { GroupCardComponent } from './components/groups/group-card/group-card.component';
import { JoinGroupComponent } from './components/join-group/join-group.component';
import { MapComponent } from './components/map/map.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { GroupSitesComponent } from './components/group-sites/group-sites.component';
import { ReviewItemComponent } from './components/comments/review-item/review-item.component';
import { MatSelectModule } from '@angular/material/select';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HomeComponent,
    GroupsComponent,
    NewReviewComponent,
    SiteCardComponent,
    CommentsComponent,
    NewSiteComponent,
    LoginComponent,
    RegisterComponent,
    NewGroupComponent,
    GroupCardComponent,
    JoinGroupComponent,
    MapComponent,
    GroupSitesComponent,
    ReviewItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatFormFieldModule,
    GoogleMapsModule,
    MatSelectModule
  ],
  entryComponents: [
    NewSiteComponent,
    NewReviewComponent,
    MapComponent,
    NewGroupComponent,
    JoinGroupComponent
  ],
  providers: [
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}},
    AppComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
