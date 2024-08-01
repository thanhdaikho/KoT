const port = 4000;
const express = require('express')
const app = express()
const mongoose = require('mongoose')
const multer = require('multer')
const path = require('path')
const cors = require('cors')

app.use(express.json())
app.use(cors())

mongoose.connect("mongodb+srv://phucthanh:phucthanh@cluster0.l4knozw.mongodb.net/kot_database");

app.get("/", (req, res) => {
    res.send('Express app is running')
})

const storage = multer.diskStorage({
    destination: './upload/images',
    filename: (req, file, cb) => {
        return cb(null, `${file.fieldname}_${Date.now()}${path.extname(file.originalname)}`)
    }
})
const upload = multer({ storage: storage })

app.use(`/images`, express.static('upload/images'))

app.post("/upload", upload.array('images', 10), (req, res) => {
    if (!req.files || req.files.length === 0) {
        return res.status(400).json({ success: 0, message: 'No files uploaded' });
    }

    let image_urls = req.files.map(file => `http://localhost:${port}/images/${file.filename}`);
    res.json({
        success: 1,
        image_urls: image_urls
    });
});

const Product = mongoose.model("Product", {
    id: {
        type: Number,
        required: true,
    },
    name: {
        type: String,
        required: true,
    },
    description: {
        type: String,
        required: true
    },
    images: {
        type: [String], // Thay đổi thành mảng String
        required: true,
    },
    category: {
        type: String,
        required: true,
    },
    new_price: {
        type: Number,
        required: true,
    },
    old_price: {
        type: Number,
        required: true,
    },
    date: {
        type: Date,
        default: Date.now,
    },
    available: {
        type: Boolean,
        default: true,
    },
})

app.get('/allproducts', async (req, res) => {
    let products = await Product.find({})
    res.send(products)
})

app.post('/addproduct', async (req, res) => {
    let products = await Product.find({});
    let id = products.length > 0 ? products.slice(-1)[0].id + 1 : 1;

    const product = new Product({
        id: id,
        name: req.body.name,
        description: req.body.description,
        images: req.body.images,
        category: req.body.category,
        new_price: req.body.new_price,
        old_price: req.body.old_price,
    });

    await product.save();
    res.json({
        success: true,
        name: req.body.name
    });
});

app.post('/removeproduct', async (req, res) => {
    await Product.findOneAndDelete({ id: req.body.id })
    console.log("Removed")
    res.json({
        success: true,
        name: req.body.name
    })
})
app.get('/newcollection', async (req, res) => {
    let products = await Product.find({})
    let newcollection = products.slice(1).slice(-8)
    res.send(newcollection)
})
app.get('/product/:id', async (req, res) => {
    try {
        const product = await Product.findOne({ id: req.params.id });
        if (!product) {
            return res.status(404).json({ message: 'Product not found' });
        }
        res.json(product);
    } catch (error) {
        res.status(500).json({ message: 'Server error' });
    }
});
app.get('/hotchair', async (req, res) => {
    let products = await Product.find({ category: "Chair" })
    let hot_chair = products.slice(0, 4)
    res.send(hot_chair)
})
app.get('/hottable', async (req, res) => {
    let products = await Product.find({ category: "Table" })
    let hot_table = products.slice(0, 4)
    res.send(hot_table)
})
app.get('/hotarmchair', async (req, res) => {
    let products = await Product.find({ category: "Armchair" })
    let hot_armchair = products.slice(0, 4)
    res.send(hot_armchair)
})
app.get('/hotbed', async (req, res) => {
    let products = await Product.find({ category: "Bed" })
    let hot_bed = products.slice(0, 4)
    res.send(hot_bed)
})
app.get('/hotlamp', async (req, res) => {
    let products = await Product.find({ category: "Lamp" })
    let hot_lamp = products.slice(0, 4)
    res.send(hot_lamp)
})
app.post('/addtocart', async (req, res) => {

})
app.listen(port, (error) => {
    if (!error) {
        console.log('Sever running on port 4000')
    } else {
        console.log('Error: ' + error)
    }
})
