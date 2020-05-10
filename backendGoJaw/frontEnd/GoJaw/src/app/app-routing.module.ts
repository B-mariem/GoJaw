import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SigninAdminComponent } from './admin/signin-admin/signin-admin.component';
import { ProfileAdminComponent } from './admin/profile-admin/profile-admin.component';
import { AuthGuard } from './service/auth.guard';
import { CreateGouvComponent } from './gouvernorat/create-gouv/create-gouv.component';

import { CreateEventComponent } from './evenement/create-event/create-event.component';
import { CreateDesComponent } from './des/create-des/create-des.component';

import { EditDesComponent } from './des/edit-des/edit-des.component';
import { SearchGouvComponent } from './gouvernorat/search-gouv/search-gouv.component';
import { ListGouvComponent } from './gouvernorat/list-gouv/list-gouv.component';
import { NavGouvComponent } from './gouvernorat/nav-gouv/nav-gouv.component';
import { SearchDesComponent } from './des/search-des/search-des.component';
import { ValidEventComponent } from './evenement/valid-event/valid-event.component';
import { ListEventComponent } from './evenement/list-event/list-event.component';
import { SearchEventComponent } from './evenement/search-event/search-event.component';
import { CreateCategorieComponent } from './categorie/create-categorie/create-categorie.component';


const routes: Routes = [
  { path: '', redirectTo: '/admin',pathMatch: 'full'},
  {path:"admin",component:SigninAdminComponent},
  { path: 'admin-profile/:id', component: ProfileAdminComponent, canActivate: [AuthGuard] },
  {path:"create-gouv",component:CreateGouvComponent},

  {path:"create-des",component:CreateDesComponent},
  {path:"edit-des",component:EditDesComponent},
  {path:"create-event",component:CreateEventComponent},
  
  {path:"search-Gouv/:gouv",component:SearchGouvComponent},
  {path:"list-gouv",component:ListGouvComponent},
  {path:"search-destination/:gouv",component:SearchDesComponent},
  {path:"valid-event",component:ValidEventComponent},
  {path:"list-event",component:ListEventComponent},
  {path:"search-event/:gouv",component:SearchEventComponent},
  {path:"create-categorie",component:CreateCategorieComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
