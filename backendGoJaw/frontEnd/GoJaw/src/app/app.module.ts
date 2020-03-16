import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ApiService } from './service/api.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AuthInterceptor } from './service/authconfig.interceptor';
import { ScrollToModule } from '@nicky-lenaers/ngx-scroll-to';
import { SigninAdminComponent } from './admin/signin-admin/signin-admin.component';
import { ProfileAdminComponent } from './admin/profile-admin/profile-admin.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RootNavComponent } from './root-nav/root-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { CreateEventComponent } from './evenement/create-event/create-event.component';
import { AffStattistiqueComponent } from './statistique/aff-stattistique/aff-stattistique.component';
import { AffFeedbackComponent } from './feedback/aff-feedback/aff-feedback.component';
import { CreateGouvComponent } from './gouvernorat/create-gouv/create-gouv.component';
import { CreateDesComponent } from './des/create-des/create-des.component';
import { CreateVilleComponent } from './ville/create-ville/create-ville.component';


@NgModule({
  declarations: [
    AppComponent,
    SigninAdminComponent,
    ProfileAdminComponent,
    RootNavComponent,
    CreateEventComponent,
    AffStattistiqueComponent,
    AffFeedbackComponent,
    CreateGouvComponent,
    CreateDesComponent,
    CreateVilleComponent,
 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    ScrollToModule.forRoot(),
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule
  ],
  providers: [
    ApiService,
    {
          provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
