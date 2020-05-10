const mongoose = require('mongoose');
const Schema = mongoose.Schema;
let event = new Schema({
  id_user: { type:String},
    titre:{type: String},
    date:{type:String},
    gouv:{type:String},
    createdBy:{type:String},
    params: [],
    type:{type:String},
    participants:[{type:String,
      unique:true}],
    },{
      collection: 'event'
   })
   
   module.exports = mongoose.model('event',event)