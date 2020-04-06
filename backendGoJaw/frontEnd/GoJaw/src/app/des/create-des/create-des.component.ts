import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-create-des',
  templateUrl: './create-des.component.html',
  styleUrls: ['./create-des.component.css']
})
export class CreateDesComponent implements OnInit {

  destinationForm: FormGroup;
 tabGouv:any
  constructor( public fb: FormBuilder,private apiService: ApiService ) { 
    this.apiService.getGouvs().subscribe((data)=>{
      this.tabGouv=data
  console.log(this.tabGouv);
  
    })

    this.mainForm();
  }

  updateGouv(e){
    this.destinationForm.get('gouv').setValue(e, {
      onlySelf: true
    })
  }
  mainForm() {
    this.destinationForm = this.fb.group({
      libelle: ['', [Validators.required]], 
      image: ['', [Validators.required]], 
      categorie: ['', [Validators.required]], 
      gouv: ['', [Validators.required]],
    })
  }

  onSubmit() {
    console.log(this.destinationForm.value);
    this.apiService.createDestination(this.destinationForm.value).subscribe( (res) => {
        alert('destination successfully created!')
      this.destinationForm.reset()
      
      }, (error) => {
        alert(error);
      });
  
    
  }
  ngOnInit() {
  }
}
