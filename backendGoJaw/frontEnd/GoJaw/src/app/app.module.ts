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
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material';
import { MatMenuModule } from '@angular/material/menu';
import { CreateEventComponent } from './evenement/create-event/create-event.component';

import { CreateGouvComponent } from './gouvernorat/create-gouv/create-gouv.component';
import { CreateDesComponent } from './des/create-des/create-des.component';

import { EditDesComponent } from './des/edit-des/edit-des.component';
import { MatDialogModule } from '@angular/material/dialog';
import { DialogDesComponent } from './des/dialog-des/dialog-des.component';
import { SearchGouvComponent } from './gouvernorat/search-gouv/search-gouv.component';
import { ListGouvComponent } from './gouvernorat/list-gouv/list-gouv.component';
import { NavGouvComponent } from './gouvernorat/nav-gouv/nav-gouv.component';
import { DialogEditGouvComponent } from './gouvernorat/dialog-edit-gouv/dialog-edit-gouv.component';
import { DialogDeleteGouvComponent } from './gouvernorat/dialog-delete-gouv/dialog-delete-gouv.component';
import { DesNavbarComponent } from './des/des-navbar/des-navbar.component';
import { SearchDesComponent } from './des/search-des/search-des.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { ValidEventComponent } from './evenement/valid-event/valid-event.component';
import { IgxCardModule, IgxTimePickerModule } from 'igniteui-angular';
import { NavbarEventComponent } from './evenement/navbar-event/navbar-event.component';
import { ListEventComponent } from './evenement/list-event/list-event.component';
import { SearchEventComponent } from './evenement/search-event/search-event.component';
import { CreateCategorieComponent } from './categorie/create-categorie/create-categorie.component';
import { EdtCategorieComponent } from './categorie/edt-categorie/edt-categorie.component';
import { DeleteCategorieComponent } from './categorie/delete-categorie/delete-categorie.component';

@NgModule({
  declarations: [
    AppComponent,
    SigninAdminComponent,
    ProfileAdminComponent,
    RootNavComponent,
    CreateEventComponent,
    CreateGouvComponent,
    CreateDesComponent,
    EditDesComponent,
    DialogDesComponent,
    SearchGouvComponent,
    ListGouvComponent,
    NavGouvComponent,
    DialogEditGouvComponent,
    DialogDeleteGouvComponent,
    DesNavbarComponent,
    SearchDesComponent,
    ValidEventComponent,
    NavbarEventComponent,
    ListEventComponent,
    SearchEventComponent,
    CreateCategorieComponent,
    EdtCategorieComponent,
    DeleteCategorieComponent,
   
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
    MatListModule,
    MatFormFieldModule,
    MatInputModule,
    MatMenuModule,
    MatDialogModule,
    MatGridListModule,
    IgxCardModule,
    
  ],
  entryComponents: [
    DialogDesComponent,
    DialogEditGouvComponent,
    DialogDeleteGouvComponent,
    EdtCategorieComponent,
    DeleteCategorieComponent
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
export class AppModule {
}
