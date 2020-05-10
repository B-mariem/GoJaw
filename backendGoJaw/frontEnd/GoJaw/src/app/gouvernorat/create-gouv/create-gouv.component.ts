import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';
import { Router } from '@angular/router';
import { GouvServiceService } from 'src/app/service/gouv-service/gouv-service.service';

@Component({
  selector: 'app-create-gouv',
  templateUrl: './create-gouv.component.html',
  styleUrls: ['./create-gouv.component.css']
})
export class CreateGouvComponent implements OnInit {
  gouvForm: FormGroup;
show:boolean
tabGouv:any  
  constructor(public fb: FormBuilder,
    private gouvService: GouvServiceService,
    private router: Router) { 
  

    this.mainForm();
  }

  ngOnInit() { }

  mainForm() {
    this.gouvForm = this.fb.group({
     gouv: ['', [Validators.required]],
     image: ['', [Validators.required]]
    
    })
  }

  

  // Getter to access form control
  get myForm(){
    return this.gouvForm.controls;
  }
 
  onSubmit() {
   
      this.gouvService.createGouv(this.gouvForm.value).subscribe(
        (res) => {
          alert('gouv successfully created!')
        this.gouvForm.reset()
       this.router.navigate(["list-gouv"])
        }, (error) => {
          alert(error);
        });
    
  }
 

  
}
