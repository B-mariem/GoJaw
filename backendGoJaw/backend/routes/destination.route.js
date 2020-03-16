const express = require('express');
const destinationroute = express.Router();
// gouv model
let destinationModel = require('../Models/destinationModel');

// Add destination
destinationroute.route('/create').post((req, res, next) => {
  destinationModel.create(req.body, (error, data) => {
    if (error) {
      return next(error)
    } else {
      
        res.json(data)
    }
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
//get by ville
destinationroute.route('/byVille').post((req, res) => {
    destinationModel.find({ville:req.body.ville}).then((destination) => {
        res.status(200).send(destination) 

      }).catch((err)=> {
          // If an error occurred, send it to the client
          res.json(err);
    
      })
  })
  module.exports = destinationroute;