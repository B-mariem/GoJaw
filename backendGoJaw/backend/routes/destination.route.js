const express = require('express');
const destinationroute = express.Router();
// gouv model
let destinationModel = require('../Models/destinationModel');


// Add destination
destinationroute.route('/create').post((req, res, next) => {
  destinationModel.create(req.body).then((data)=>{
    res.json(data)
  }).catch((err)=> {
    // If an error occurred, send it to the client
    res.json(err);

}) 
});
// Get All destination
destinationroute.route('/').get((req, res) => {
  destinationModel.find((error, data) => {
    if (error) {
      return next(error)
    } else { 
      res.json(data)
    }
  })
})


//get by gouv
destinationroute.route('/byGouv').post((req, res) => {
destinationModel.find(({gouv:req.body.gouv}),(error, data) => {
  if (error) {
    res.status(401)
  } else { 
    res.status(200).send(data)
  }
})
})
destinationroute.route('/read/:id').get((req, res) => {
  destinationModel.findById(req.params.id, (error, data) => {
    if (error) {
      return next(error)
    } else {
      res.json(data)
    }
  })
})


  destinationroute.route('/byGouv/:gouv').get((req, res) => {
    destinationModel.find(({gouv:req.params.gouv}),(error, data) => {
      if (error) {
        res.status(401)
      } else { 
        res.status(200).send(data)
      }
    })
      
      })

      
    
/*----update----*/
destinationroute.route('/update/:id').put((req, res, next) => {
 destinationModel.findByIdAndUpdate(req.params.id, {
     $set: req.body
   }, (error, data) => {
     if (error) {
       return next(error);
     
     } else {
       res.json(data)
       console.log('Data updated successfully')
     }
   })
 })

/*-----delete----*/
  destinationroute.route('/delete/:gouv').delete((req, res, next) => {
    destinationModel.findOneAndRemove({gouv:req.params.gouv}, (error, data) => {
      if (error) {
        return next(error);
      } else {
        res.status(200).json({
          msg: data
        })
      }
    })
  })
  module.exports = destinationroute;
 