import './ImageTours.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Carousel } from 'react-bootstrap';
import { Link, useParams } from 'react-router-dom';
import Apis, { endpoints } from '../../configs/Apis';
import { useEffect, useState } from 'react';

const ImageTours = () => {
    const { toursId } = useParams();
    const [image, setImage] = useState(null);
    let url = `/tours/${toursId}`;

    useEffect(() => {
        const loadImage = async () => {
            let { data } = await Apis.get(endpoints['imagetours'](toursId));
            setImage(data);
        }
        loadImage();
    }, [toursId]);
    console.log(image)
    console.log(toursId)
    return (
        <>
            <h1>Hình ảnh Tours</h1>
            <Carousel style={{ maxWidth:"1000px",margin:"0 auto" }}>
                {image && image.map((p, index) => (
                    <Carousel.Item  id="carousel-item" key={index}>
                        <img style={{display:"block",margin:"0 auto"}}  max-width="100%" height="600px" src={p.imageUrl} alt={`Hình ảnh Tours ${index}`} />
                    </Carousel.Item>
                ))}
            </Carousel>
            
            <Link to={url} style={{ textDecoration: "none" }}><p className='text text-center text-primary mt-3' >Quay lại</p></Link>
        </>
    )
}

export default ImageTours;