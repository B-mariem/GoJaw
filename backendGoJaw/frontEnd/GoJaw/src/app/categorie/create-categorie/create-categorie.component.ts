import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { DialogEditGouvComponent } from 'src/app/gouvernorat/dialog-edit-gouv/dialog-edit-gouv.component';
import { EdtCategorieComponent } from '../edt-categorie/edt-categorie.component';
import { DeleteCategorieComponent } from '../delete-categorie/delete-categorie.component';
import { CategorieServiceService } from 'src/app/service/categorie-service/categorie-service.service';

@Component({
  selector: 'app-create-categorie',
  templateUrl: './create-categorie.component.html',
  styleUrls: ['./create-categorie.component.css']
})
export class CreateCategorieComponent implements OnInit {
  categorieForm: FormGroup;
  tabCategorie:any;
   
  constructor(public fb: FormBuilder,private categorieService: CategorieServiceService,public dialog: MatDialog,private router:Router) { 
      this.mainForm();
      this.categorieService.getCategories().subscribe((data)=>{
        this.tabCategorie=data
      })
    }
  
    ngOnInit() { }
  
    mainForm() {
      this.categorieForm = this.fb.group({
       libelle: ['', [Validators.required]],
      })
    }
  
    
  
    // Getter to access form control
    get myForm(){
      return this.categorieForm.controls;
    }
   
    onSubmit() {
     
        this.categorieService.createCategorie(this.categorieForm.value).subscribe( () => {
          location.reload()
            alert('category successfully created!')

          this.categorieForm.reset()
      
        // this.router.navigate(["/create-categorie"])
          }, (error) => {
            alert(error);
          });
      
    }
    openDialogEditCategorie(value): void {
      const dialogRef = this.dialog.open(EdtCategorieComponent, {
        data:{
          id:value
        }  
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
         location.reload();
        console.log(result);
        
      
      });
    }
  
    openDialogDeleteCategorie(value): void {
      const dialogRef = this.dialog.open(DeleteCategorieComponent, {
        data:{
          id:value
        }  
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
         location.reload();  
      });
    }
  }