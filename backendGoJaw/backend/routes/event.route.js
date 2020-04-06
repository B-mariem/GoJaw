const express = require('express');
const eventroute = express.Router();

// eventmodel
let EventModel = require('../Models/eventModel');

// Add gouvernorats
eventroute.route('/create').post((req, res, next) => {

  EventModel.create(req.body, (error, data) => {
    if (error) {
      return next(error)
    } else {
      res.json(data)
    }
  })
});
module.exports = eventroute;
