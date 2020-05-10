const express = require('express');
const categorieRoute = express.Router();

// model
let categorieModel = require('../Models/categorieModel');

// Add 
categorieRoute.route('/create').post((req, res, next) => {
  categorieModel.create(req.body, (error, data) => {
    if (error) {
      return next(error)
    } else {
      res.status(200).send(data)
    }
  })
});
// Get All 
categorieRoute.route('/all').get((req, res) => {
    categorieModel.find((error, data) => {
      if (error) {
        return next(error)
      } else {
        res.status(200).send(data)
      }
    })
  }) 
  // Get single 
  categorieRoute.route('/read/:id').get((req, res) => {
    categorieModel.findById(req.params.id, (error, data) => {
      if (error) {
        return next(error)
      } else {
        res.json(data)
      }
    })
  })
  
  
  /*-------------edit-------------*/
  categorieRoute.route('/update/:id').put((req, res, next) => {
   categorieModel.findByIdAndUpdate(req.params.id, {
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
  // Delete
  categorieRoute.route('/delete/:id').delete((req, res, next) => {
    categorieModel.findOneAndRemove({_id:req.params.id}, (error, data) => {
      if (error) {
        return next(error);
      } else {
        res.status(200).json("success")
      }
    })
  })
  module.exports = categorieRoute;