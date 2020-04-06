const mongoose = require("mongoose");

const Destination = mongoose.model(
  "Destination",
  new mongoose.Schema({
    libelle:{type: String,unique:true,lowercase: true,required:true},
    image:{type:String,unique:true,required:true},
    categorie:{type:String,required:true},
    gouv:{type:String,required:true}, 
    position: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "Position"
    }
  })
);

module.exports = Destination;