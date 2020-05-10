import { Component, OnInit, Input } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { destination } from 'src/app/models/destinations';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { DestinationServiceService } from 'src/app/service/destination-service/destination-service.service';
import { EventServiceService } from 'src/app/service/event-service/event-service.service';

@Component({
  selector: 'app-valid-event',
  templateUrl: './valid-event.component.html',
  styleUrls: ['./valid-event.component.css']
})
export class ValidEventComponent implements OnInit {
  @Input() titre: any;
  @Input() gouv:any;
  @Input() date:any;
  tabParams=[]
  tabDestination:destination[]
  obj:Event
  constructor( public eventService:EventServiceService,public fb: FormBuilder,private router:Router,
    private desService:DestinationServiceService) {
   
  
    
    }
  ngOnInit() {
    console.log(this.gouv,this.titre,this.date);
    this.desService.getDestinationByGouv(this.gouv).subscribe((data)=>{
      this.tabDestination=data
      this.tabDestination.sort((val1,val2)=>{{return (val1.latitude) -(val2.latitude)}

      })  
    }) 
  }

  add(va1,va2:destination){
      const objt={
        tempsArrive:va1,
        destination:va2.libelle,
         image:va2.image}
       let index=this.tabDestination.indexOf(va2)
      this.tabDestination.splice(index,1)     
      this.tabParams.push(objt)
      alert("Vous avez choisi "+va2.libelle+"à"+va1)

  }
Save(){
if(this.tabParams.length>0){
  const obj={
    titre:this.titre,
    date:this.date,
    gouv:this.gouv,
    params:this.tabParams,
    type:"Public",
    createdBy:"Admin"
   }
  
 this.eventService.createEventByAdmin(obj).subscribe((res)=>{
   if(res){
     this.router.navigate(["list-event"])
     alert("success")
    
   }   
 })
}else{
  alert ('choisi une destination')
}
  
   
 }
 back(){

   this.router.navigate(["create-event"])
   console.log("mariem");
   
  // this.tabParams=[]
   
  }
}
