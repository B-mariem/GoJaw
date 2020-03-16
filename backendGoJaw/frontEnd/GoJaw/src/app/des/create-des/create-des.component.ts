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
 tabVille:any
  constructor( public fb: FormBuilder,private apiService: ApiService ) { 
    this.apiService.getVilles().subscribe((data)=>{
      this.tabVille=data
  console.log(this.tabVille);
  
    })

    this.mainForm();
  }

  updateVille(e){
    this.destinationForm.get('ville').setValue(e, {
      onlySelf: true
    })
  }
  mainForm() {
    this.destinationForm = this.fb.group({
      destination: ['', [Validators.required]], 
      image: ['', [Validators.required]], 
      categorie: ['', [Validators.required]], 
      ville: ['', [Validators.required]],
    })
  }

  onSubmit() {
    console.log(this.destinationForm.value);
    this.apiService.createDestination(this.destinationForm.value).subscribe(
      (res) => {
        alert('destination successfully created!')
      this.destinationForm.reset()
      
      }, (error) => {
        alert(error);
      });
  
    
  }
  ngOnInit() {
  }
}
