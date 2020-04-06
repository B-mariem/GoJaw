const express = require('express');
const villeroute = express.Router();
// gouv model
let villeModel = require('../Models/villeModel');

// Add ville
villeroute.route('/create').post((req, res, next) => {
  villeModel.create(req.body, (error, data) => {
    if (error) {
      return next(error)
    } else {
      
        res.json(data)
    }
  })
});
// Get All ville
villeroute.route('/').get((req, res) => {
  villeModel.find((error, data) => {
    if (error) {
      return next(error)
    } else {
      res.json(data)
    }
  })
})
//get by gouv
villeroute.route('/byGouv').post((req, res) => {
    villeModel.find(({gouvernorat:req.body.gouvernorat}),(error, data) => {
      if (error) {
        res.status(404)
      } else { 
        res.status(200).send(data)
      }
    }).populate("position","-_id -__v")
      
      })
// Update ville
villeroute.route('/update/:id').put((req, res, next) => {
  villeModel.findByIdAndUpdate(req.params.id, {
    $set: req.body
  }, (error, data) => {
    if (error) {
      return next(error);
      console.log(error)
    } else {
      res.json(data)
      console.log('Data updated successfully')
    }
  })
})
// Delete ville
villeroute.route('/delete/:id').delete((req, res, next) => {
  villeModel.findOneAndRemove(req.params.id, (error, data) => {
    if (error) {
      return next(error);
    } else {
      res.status(200).json({
        msg: data
      })
    }
  })
})
module.exports = villeroute;