
import 'bootstrap/dist/css/bootstrap.min.css';
import { Carousel } from 'react-bootstrap';


const Home = () => {
    return (
        <Carousel cols={2} rows={1} gap={10} loop>
            <Carousel.Item>
                <img width="100%" height="700px" src="https://res.cloudinary.com/dogkiegre/image/upload/v1692027736/ywjicu2dssfr5nitq5ft.jpg" />
            </Carousel.Item>
            <Carousel.Item>
                <img width="100%" height="700px" src="https://static.bambooairways.com/wp-content/uploads/sites/6/20230719191718/du-lich-ky-co-eo-gio-3.jpg"/>
            </Carousel.Item>
            <Carousel.Item>
                <img width="100%" height="700px" src="https://statics.vinpearl.com/kinh-thanh-hue-4_1690187089.jpg" />
            </Carousel.Item>
        </Carousel>
    )
}
export default Home;