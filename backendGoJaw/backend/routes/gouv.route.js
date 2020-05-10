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

/*------------------search---------------------/*/
gouvernoratroute.route('/search/:gouv').get((req, res) => {
  var query = { gouv: req.params.gouv };
  gouvernoratModel.find(query, (error, data) => {
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


/*-------------edit-------------*/
gouvernoratroute.route('/update/:id').put((req, res, next) => {
 gouvernoratModel.findByIdAndUpdate(req.params.id, {
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
// Delete Gouv
gouvernoratroute.route('/delete/:gouv').delete((req, res, next) => {
  gouvernoratModel.findOneAndRemove({gouv:req.params.gouv}, (error, data) => {
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