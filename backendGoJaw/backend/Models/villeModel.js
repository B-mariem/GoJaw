var mongoose=require('mongoose');
//schema maps to a MongoDB collection 
var Schema=mongoose.Schema;
//the Schema( structure of document/data) in MongoDB by the use of mongoose.Schema.
var villeschema=new Schema(
    {   
        ville:{
            type: String,
            unique:true,
            lowercase: true,
            required:true

        },
        image:{
            type:String,
            unique:true,
            required:true


        },
        gouvernorat:{
            type:String,
            required:true

        },
        position: {
            type: mongoose.Schema.Types.ObjectId,
            ref: "Position"
          }
    },
    {
        collection: 'villes'
     })

// export the schema 
module.exports = mongoose.model('villes', villeschema)