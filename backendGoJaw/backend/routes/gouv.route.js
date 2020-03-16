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
      res.json(data)
    }
  })
});

// Get All gouvernorats
gouvernoratroute.route('/').get((req, res) => {
  gouvernoratModel.find((error, data) => {
    if (error) {
      return next(error)
    } else {
      res.json(data)
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

/////////////
gouvernoratroute.route('/:id').get((req, res) => {
    gouvernoratModel.findById(req.params.id).populate('gouvernortas')
    gouvernoratModel.find((error, data) => {
        if (error) {
          return next(error)
        } else {
          res.json(data)
        }
      })
    })

// Update gouvernorat
gouvernoratroute.route('/update/:id').put((req, res, next) => {
  gouvernoratModel.findByIdAndUpdate(req.params.id, {
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

// Delete gouvernorat
gouvernoratroute.route('/delete/:id').delete((req, res, next) => {
  gouvernoratModel.findOneAndRemove(req.params.id, (error, data) => {
    if (error) {
      return next(error);
    } else {
      res.status(200).json({
        msg: data
      })
    }
  })
})

module.exports = gouvernoratroute;