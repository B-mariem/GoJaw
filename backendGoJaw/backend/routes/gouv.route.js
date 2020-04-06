const express = require('express');
const gouvernoratroute = express.Router();

// gouv model
let gouvernoratModel = require('../Models/gouvModel');

// Add gouvernorats
gouvernoratroute.route('/create').post((req, res, next) => {
  gouvernoratModel.create(req.body, (error, data) => {
    if (error) {
      return next(error)
    } else {
      res.status(200).send(data)
    }
  })
});

// Get All gouvernorats
gouvernoratroute.route('/all').get((req, res) => {
  gouvernoratModel.find((error, data) => {
    if (error) {
      return next(error)
    } else {
      res.status(200).send(data)
    }
  })
})

// Get single gouvernorat
gouvernoratroute.route('/read/:id').get((req, res) => {
  gouvernoratModel.findById(req.params.id, (error, data) => {
    if (error) {
      return next(error)
    } else {
      res.json(data)
    }
  })
})

/////vile_gouv
  ////// ville _gou
  gouvernoratroute.post("/add_ville/:id", (req, res, next) => {
    gouvernoratModel.findOneAndUpdate({_id:req.params.id},
           {$push:{ ville:req.body}}).then((gouvernorats) =>{
             res.json(gouvernorats);
         })
         .catch((err)=> {
         
           res.json(err);
         });
       });



module.exports = gouvernoratroute;