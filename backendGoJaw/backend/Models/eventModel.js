const mongoose = require('mongoose');
const Schema = mongoose.Schema;
let event = new Schema({
  id_user: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "user"
  },
  titre:{type: String,required:true},
    date:{type:String},
    destinations: {
      type: mongoose.Schema.Types.Array,
      ref: "Destination"
    },
    type:{type:String},
  },{
      collection: 'event'
   })
   
   module.exports = mongoose.model('event',event)