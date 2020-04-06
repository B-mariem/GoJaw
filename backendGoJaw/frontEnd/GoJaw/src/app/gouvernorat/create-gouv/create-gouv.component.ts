import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ApiService } from 'src/app/service/api.service';

@Component({
  selector: 'app-create-gouv',
  templateUrl: './create-gouv.component.html',
  styleUrls: ['./create-gouv.component.css']
})
export class CreateGouvComponent implements OnInit {
  gouvForm: FormGroup;
show:boolean
tabGouv:any  
  constructor(public fb: FormBuilder,private apiService: ApiService) { 
    this.apiService.getGouvs().subscribe((data)=>{
      this.tabGouv=data
    })

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
  showDevAdd(){
    this.show= !this.show;
  }
  onSubmit() {
   
      this.apiService.createGouv(this.gouvForm.value).subscribe(
        (res) => {
          alert('gouv successfully created!')
        this.gouvForm.reset()
        location.reload()
        }, (error) => {
          alert(error);
        });
    
  }
}
