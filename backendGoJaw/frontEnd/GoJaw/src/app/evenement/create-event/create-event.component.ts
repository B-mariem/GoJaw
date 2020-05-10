import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { log } from 'util';
import { ifStmt } from '@angular/compiler/src/output/output_ast';
import { GouvServiceService } from 'src/app/service/gouv-service/gouv-service.service';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.css']
})
export class CreateEventComponent implements OnInit {
  eventForm: FormGroup;
  show:boolean
  gouv:any
  date:any
  titre:any
  tabGouv:any
  

   constructor( public fb: FormBuilder,private apiService: ApiService,private gouvService :GouvServiceService ) { 

    this.gouvService.getGouvs().subscribe((data)=>{
      this.tabGouv=data
  
    })
     this.show=false
 
     this.mainForm();
   }
 
   updateGouv(e){
    this.eventForm.get('gouv').setValue(e, {
      onlySelf: true
    })
  }
   mainForm() {
     this.eventForm = this.fb.group({
       titre: ['', [Validators.required]], 
       date: ['', [Validators.required]], 
       gouv:['', [Validators.required]], 
       
     })
   }
 
   onSubmit() { 
       this.createEvent()
   }

   createEvent(){
    let currentDate=new Date()
    let selectedDate=new Date(this.eventForm.get("date").value)
    if(this.verifDate(currentDate,selectedDate)==true){
      this.gouv=this.eventForm.get("gouv").value
      this.titre=this.eventForm.get("titre").value
      this.date=this.eventForm.get("date").value

      this.show=true
     /* this.apiService.createEventByAdmin(this.eventForm.value).subscribe((res)=>{
        if(res){
          this.idEvent=res.id
          this.gouv=res.gouv
          this.show=true
        }  
      })*/
    }else{
      alert("echec")
      this.eventForm.reset()
        }

   }

   verifDate(currentDate:any,selectedDate:any):boolean{
    if(currentDate > selectedDate){
     return false
    }else{
     return true 
     
    }
   }
   ngOnInit() {
   }
 }