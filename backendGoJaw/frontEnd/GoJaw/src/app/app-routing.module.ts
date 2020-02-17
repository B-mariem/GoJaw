import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SigninAdminComponent } from './admin/signin-admin/signin-admin.component';
import { ProfileAdminComponent } from './admin/profile-admin/profile-admin.component';
import { AuthGuard } from './service/auth.guard';
import { CreateGouvComponent } from './gouvernorat/create-gouv/create-gouv.component';
import { CreateVilleComponent } from './ville/create-ville/create-ville.component';
import { CreateEventComponent } from './evenement/create-event/create-event.component';
import { CreateDesComponent } from './des/create-des/create-des.component';
import { AffStattistiqueComponent } from './statistique/aff-stattistique/aff-stattistique.component';
import { AffFeedbackComponent } from './feedback/aff-feedback/aff-feedback.component';


const routes: Routes = [
  { path: '', redirectTo: '/admin',pathMatch: 'full'},
  {path:"admin",component:SigninAdminComponent},
  { path: 'admin-profile/:id', component: ProfileAdminComponent, canActivate: [AuthGuard] },
  {path:"create-gouv",component:CreateGouvComponent},
  {path:"create-ville",component:CreateVilleComponent},
  {path:"create-des",component:CreateDesComponent},
  {path:"create-event",component:CreateEventComponent},
  {path:"statistique",component:AffStattistiqueComponent},
  {path:"feedback",component:AffFeedbackComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
