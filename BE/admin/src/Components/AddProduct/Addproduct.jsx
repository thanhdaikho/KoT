import React, { useState } from 'react';
import './Addproduct.css';
import upload_area from '../../assets/upload_area.svg';

const Addproduct = () => {
  const [images, setImages] = useState([]);
  const [productDetails, setProductDetails] = useState({
    name: "",
    description: "",
    images: [],
    category: "Chair",
    new_price: "",
    old_price: ""
  });

  const imageHandler = (e) => {
    setImages([...e.target.files]);
  };

  const changeHandler = (e) => {
    setProductDetails({ ...productDetails, [e.target.name]: e.target.value });
  };

  const Add_Product = async () => {
    let responseData;
    let formData = new FormData();
    images.forEach((image, index) => {
      formData.append('images', image);
    });

    await fetch('http://localhost:4000/upload', {
      method: 'POST',
      body: formData,
    }).then((resp) => resp.json()).then((data) => { responseData = data });

    if (responseData.success) {
      const product = {
        ...productDetails,
        images: responseData.image_urls
      };

      await fetch('http://localhost:4000/addproduct', {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(product)
      });
    } else {
      console.log('FAILED');
    }
  };

  return (
    <div className='add-product'>
      <div className='addproduct-itemfield'>
        <p>Tiêu đề sản phẩm</p>
        <input value={productDetails.name} onChange={changeHandler} type='text' name='name' placeholder='Type here' />
      </div>
      <div className='addproduct-itemfield'>
        <p>Mô tả sản phẩm</p>
        <input value={productDetails.description} onChange={changeHandler} type='text' name='description' placeholder='Type here' />
      </div>
      <div className='addproduct-price'>
        <div className='addproduct-itemfield'>
          <p>Giá</p>
          <input value={productDetails.old_price} onChange={changeHandler} type='text' name='old_price' placeholder='Type here' />
        </div>
        <div className='addproduct-itemfield'>
          <p>Giá khuyến mãi</p>
          <input value={productDetails.new_price} onChange={changeHandler} type='text' name='new_price' placeholder='Type here' />
        </div>
      </div>
      <div className='addproduct-itemfield'>
        <p>Danh mục sản phẩm</p>
        <select value={productDetails.category} onChange={changeHandler} name='category' className='add-product-selector'>
          <option value='Chair'>Chair</option>
          <option value='Table'>Table</option>
          <option value='Armchair'>Armchair</option>
          <option value='Bed'>Bed</option>
          <option value='Lamp'>Lamp</option>
        </select>
      </div>
      <div className='addproduct-itemfield'>
        <label htmlFor='file-input'>
          <img src={images.length > 0 ? URL.createObjectURL(images[0]) : upload_area} className='addproduct-thumbnail-img' />
        </label>
        <input onChange={imageHandler} type='file' name='images' id='file-input' multiple hidden />
      </div>
      <button onClick={Add_Product} className='addproduct-btn'>Thêm</button>
    </div>
  );
};

export default Addproduct;
