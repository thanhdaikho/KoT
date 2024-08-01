import React, { useEffect, useState } from 'react'
import './Listproduct.css'
import cross_icon from '../../assets/cross_icon.png'

const Listproduct = () => {
  const [allproducts, setAllProducts] = useState([])

  const fetchInfo = async () => {
    await fetch('http://localhost:4000/allproducts')
      .then((res) => res.json())
      .then((data) => { setAllProducts(data) })
  }

  const remove_product = async (id) => {
    await fetch('http://localhost:4000/removeproduct', {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ id: id })
    })
    await fetchInfo()
  }

  useEffect(() => {
    fetchInfo()
  }, [])

  return (
    <div className='list-product'>
      <h1>Tất Cả Sản Phẩm</h1>
      <div className='listproduct-format-main'>
        <p>Sản phẩm</p>
        <p>Tiêu đề</p>
        <p>Mô tả</p>
        <p>Giá cũ</p>
        <p>Giá mới</p>
        <p>Phân loại</p>
        <p>Xóa</p>
      </div>
      <div className='listproduct-allproducts'>
        <hr />
        {allproducts.map((product, index) => (
          <React.Fragment key={index}>
            <div className='listproduct-format-main listproduct-format'>
              <img 
                src={product.images && product.images.length > 0 ? product.images[0] : ''}
                alt='Product'
                className='listproduct-product-icon' 
              />
              <p>{product.name}</p>
              <p>{product.description}</p>
              <p>${product.old_price}</p>
              <p>${product.new_price}</p>
              <p>{product.category}</p>
              <img onClick={() => { remove_product(product.id) }} className='listproduct-remove-icon' src={cross_icon} alt='Remove' />
            </div>
            <hr />
          </React.Fragment>
        ))}
      </div>
    </div>
  )
}

export default Listproduct
