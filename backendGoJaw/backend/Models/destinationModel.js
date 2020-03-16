var mongoose=require('mongoose');
//schema maps to a MongoDB collection 
var Schema=mongoose.Schema;
//the Schema( structure of document/data) in MongoDB by the use of mongoose.Schema.
var destinationchema=new Schema(
    {   
        destination:{
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
        categorie:{
            type:String,
            unique:true,
            required:true


        },
        ville:{
            type:String,
            required:true

        } 
    },
    {
        collection: 'destinations'
     })

// export the schema 
module.exports = mongoose.model('destinations', destinationchema)