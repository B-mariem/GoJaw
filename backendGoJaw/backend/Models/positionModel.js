const mongoose = require("mongoose");

const Position = mongoose.model(
  "Position",
  new mongoose.Schema({
    latitude: Number,
    longitude: Number
   
  })
);

module.exports = Position;
//AIzaSyCnwaykErMCA6NeeMtcWHSRR2BBJUB7n1E