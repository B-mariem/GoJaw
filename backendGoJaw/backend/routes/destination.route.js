const express = require('express');
const destinationroute = express.Router();
// gouv model
let destinationModel = require('../Models/destinationModel');
let positionModel = require('../Models/positionModel');

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
  }).populate("position","-_id -__v");
})

destinationroute.route("/addPosition/:id").post((req, res)=> {
  positionModel.create(req.body).then((position) =>{
      return destinationModel.findOneAndUpdate({ _id: req.params.id }, {$push: {position: position._id}}, { new: true });
    }).then((destination)=> {
       res.json(destination);
    })
    .catch((err) =>{
      res.json(err);
    });
});
//get by gouv
destinationroute.route('/byGouv').post((req, res) => {

destinationModel.find(({gouv:req.body.gouv}),(error, data) => {
  if (error) {
    res.status(401).send("pas")
  } else { 
    res.status(200).send(data)
  }
}).populate("position","-_id -__v").select("-__v -gouv")
  
  })
  module.exports = destinationroute;
 