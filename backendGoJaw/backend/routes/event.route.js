const express = require('express');
const eventroute = express.Router();

// eventmodel
let EventModel = require('../Models/eventModel');
let nbParticipant=2


// Add event 
eventroute.route('/create').post((req, res, next) => {
  EventModel.create(req.body.event, (error, data) => {
    if (error) {
      return next(error)
    } else {
      res.json(data)
    }
  })
});


//get All public
eventroute.route('/allPublic').get((req, res) => {
  EventModel.find({type:"Public"},(error, data) => {
     if (error) {
       return next(error)
     } else {
       res.status(200).send(data)
     }
   })
 })

  // get event createdBy user
  eventroute.route('/myEvents').post((req, res) => {
    EventModel.find(({id_user:req.body.id_user}),(error, data) => {
        if (error) {
          res.status(401)
        } else { 
          res.status(200).send(data)
        }
      })
      })
//get event by id
  eventroute.route('/byID').post((req, res) => {
    EventModel.findById(req.body.id,(error, data) => {
        if (error) {
          res.status(401)
        } else { 
          res.status(200).send(data)
        }
      })
      })

 
eventroute.route('/createEventByAdmin').post((req, res, next) => {
 
    EventModel.create(req.body, (error, data) => {
      if (error) {
        return next(error)
      } else {
          res.json(data)
      }
    })
  });
  

//search event (admin)
  eventroute.route('/byGouv/:gouv').get((req, res) => {
    EventModel.find(({gouv:req.params.gouv}),(error, data) => {
      if (error) {
        res.status(401)
      } else { 
        res.status(200).send(data)
      }
    })
      
      })
  
      
//get event  pour participer 
eventroute.route('/byGouv').post((req, res) => {
  EventModel.find(({gouv:req.body.gouv,type:"Public",
                    id_user:{$ne:req.body.idUser},
                    participants:{$ne:req.body.idUser},
                    $where: "this. participants.length < 2 "}),(error, data) => {
      if (error) {
        res.status(401)
      } else { 
        res.status(200).send(data)
      }
    })
    })

    eventroute.route('/privateEvents').post((req, res) => {
      EventModel.find(({type:"Pravite",
                        id_user:req.body.idUser})
                        ,(error, data) => {
          if (error) {
            res.status(401)
          } else { 
            res.status(200).send(data)
          }
        })
        })

    eventroute.route('/participatedEvents').post((req, res) => {
      EventModel.find({participants:req.body.idUser} ,(error, data) => {
          if (error) {
            res.status(401)
          } else { 
            res.status(200).send(data)
          }
        })
        })

      //paticiper
eventroute.route('/participe').post((req,res)=>{
  EventModel.findOneAndUpdate({_id:req.body.idEvent},
    {$push: {participants: req.body.idParticipant}}, { new: true }).then((event)=>{  
        res.status(200).json(event)
   
  }).catch((err) =>{
    res.status(401).json(err)
  });

}) 

eventroute.route('/desabonne').post((req,res)=>{
  EventModel.findOneAndUpdate({_id:req.body.idEvent},
    {$pull: {participants: req.body.idParticipant}}, { new: true }).then((event)=>{  
        res.status(200).json(event)
   
  }).catch((err) =>{
    res.status(401).json(err)
  });

})

//delete
eventroute.route('/delete').post((req, res, next) => {
  EventModel.findOneAndRemove({_id:req.body.id,$where: "this. participants.length == 0 "}, (error, data) => {
    if (error) {
      return next(error);
    } else {
      res.status(200).json({
        msg: data
      })
    }
  })
}) 

module.exports = eventroute;
