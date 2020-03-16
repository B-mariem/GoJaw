import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-create-ville',
  templateUrl: './create-ville.component.html',
  styleUrls: ['./create-ville.component.css']
})
export class CreateVilleComponent implements OnInit {

 villeForm: FormGroup;
 tabGouv:any
  constructor( public fb: FormBuilder,private apiService: ApiService ) { 
    this.apiService.getGouvs().subscribe((data)=>{
      this.tabGouv=data
  console.log(this.tabGouv);
  
    })

    this.mainForm();
  }

  updateProfile(e){
    this.villeForm.get('gouvernorat').setValue(e, {
      onlySelf: true
    })
  }
  mainForm() {
    this.villeForm = this.fb.group({
      ville: ['', [Validators.required]], 
      image: ['', [Validators.required]], 
      gouvernorat: ['', [Validators.required]],
    })
  }

  onSubmit() {
    console.log(this.villeForm.value);
    this.apiService.createVille(this.villeForm.value).subscribe(
      (res) => {
        alert('ville successfully created!')
      this.villeForm.reset()
      
      }, (error) => {
        alert(error);
      });
  
    
  }
  ngOnInit() {
  }

}
