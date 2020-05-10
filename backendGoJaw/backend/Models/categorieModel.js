var mongoose=require('mongoose');
var Schema=mongoose.Schema;
var categorieSchema=new Schema(
    {
    
        libelle:{
            type: String,
            unique:true,
            lowercase: true,
            required:true
        },    
    },
    {
        collection: 'categories'
     })

// export the schema 
module.exports = mongoose.model('categories', categorieSchema)