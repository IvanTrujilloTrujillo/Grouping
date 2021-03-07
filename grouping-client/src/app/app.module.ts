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
import { BarRatingModule } from "ngx-bar-rating";
import { MatFormFieldModule } from '@angular/material/form-field';


@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HomeComponent,
    GroupsComponent,
    NewReviewComponent,
    SiteCardComponent,
    CommentsComponent,
    NewSiteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    BarRatingModule,
    MatFormFieldModule
  ],
  entryComponents: [
    NewSiteComponent
  ],
  providers: [
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}},
    AppComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
