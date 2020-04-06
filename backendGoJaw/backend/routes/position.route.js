const express = require('express');
const positionroute = express.Router();
// gouv model
let positionModel = require('../Models/positionModel');


// Get All position
positionroute.route('/').get((req, res) => {
  positionModel.find((error, data) => {
    if (error) {
      return next(error)
    } else {
      res.json(data)
    }
  })
})

  module.exports = positionroute;