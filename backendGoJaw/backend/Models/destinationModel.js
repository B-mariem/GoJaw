const mongoose = require("mongoose");

const Destination = mongoose.model(
  "Destination",
  new mongoose.Schema({
    libelle:{type: String,unique:true,lowercase: true,required:true},
    image:{type:String,unique:true,required:true},
    categorie:{type:String,required:true},
    gouv:{type:String,required:true}, 
    latitude:{type:Number,required:true},
    longitude:{type:Number,required:true},
  })
);

module.exports = Destination;