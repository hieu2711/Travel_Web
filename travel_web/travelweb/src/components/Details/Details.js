import { Button } from "flowbite-react";
import { useContext, useEffect, useState } from "react";
import { Container, Form, ListGroup } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";
import MySpinner from "../../layout/MySpinner";
import Apis, { authApi, endpoints } from "../../configs/Apis";
import './Details.css';
import Moment from "react-moment";
import { MyUserContext } from "../../App";
import StarRating from "../Star/StarRating";

const Details = () => {
    const [user,] = useContext(MyUserContext);
    const { toursId } = useParams();
    const [product, setProduct] = useState(null);
    const [comments, setComments] = useState(null);
    const [content, setContent] = useState();
    const [rating, setRating] = useState(null);
    const starValues = [1, 2, 3, 4, 5];
    const [ratingStar, setRatingStar] = useState(0);
    const handleStarClick = (value) => {
        setRatingStar(value);
    };
    let url = `/tours/image/${toursId}`;

    useEffect(() => {
        const loadProduct = async () => {
            let { data } = await Apis.get(endpoints['details'](toursId));
            setProduct(data);
            console.log(data)
        }
        const loadComments = async () => {
            let { data } = await Apis.get(endpoints['comments'](toursId));
            setComments(data);
        }
        const loadRating = async () => {
            let { data } = await Apis.get(endpoints['rating'](toursId));
            let rating = setRating(data)
            console.log(data)
        }
        
        loadRating();
        loadProduct();
        loadComments();
    }, [toursId]);

    const addComment = () => {
        const process = async () => {
            let { data } = await authApi().post(endpoints['add-comment'], {
                "content": content,
                "tourId": product.id
            });
            console.log(data)
            setComments([...comments, data]);
            setContent("");
        }

        process();
    }
    const handleSubmitRating = () => {
        const addRating = async () => {
            let {data} = await authApi().post(endpoints['add-rating'],{
                "rating":ratingStar,
                "tourId":product.id
            })
        }
        addRating();
        alert(`Bạn đã đánh giá ${ratingStar} sao.`);
      };

    if (product === null || comments === null)
        return <MySpinner />;
    let url2 = `/login?next=/tours/${toursId}`;
    let url3 = `/tours/receipts/${toursId}`
    console.log(product.id)
    const priceAdult = product.priceId.priceAdult;
    const formatPriceAdult = priceAdult.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
    const priceChild = product.priceId.priceChild;
    const formatPriceChild = priceChild.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
    return (
        
        <Container style={{ border: "1px solid #BBBBBB", position: "relative", borderRadius: "10px", overflow: "auto", marginBottom: "50px" }}>
            <h1>Chi tiết Tours</h1>
            <StarRating rating={rating} />
            <h2>{product.name}</h2>
            <div style={{ display: "flex" }}>
                <img style={{ height: "25px", marginRight: "8px" }} src="	https://elitetour.com.vn/Assets/img/index/fixAdd/f5.png" />
                <p style={{ marginRight: "30px", fontWeight: "600" }}>Nơi đi: <span style={{ marginLeft: "10px" }}>{product.departure}</span></p>
                <p style={{ fontWeight: "600" }}>Nơi đến: <span style={{ marginLeft: "10px" }}>{product.destination}</span></p>
            </div>
            <div>
                <img style={{ marginLeft: "120px" }} width="80%" height="550px" src={product.image} />
                <Link style={{ marginLeft: "975px", textDecoration: "none", color: "black" }} to={url}>Xem toàn bộ ảnh</Link>
                <Link className="btn btn-success" to={url3} type="submit" style={{marginLeft:"310px",width:"50%",borderRadius: "10px", fontSize: "20px", color: "white" }}>
                    Đặt Tours
                </Link>
                <br />
            </div>
            <div style={{ textAlign: "center" }}>
                <hr/>
                <h1 style={{ marginBottom: "20px" }} >Mô tả</h1>
                <p style={{ width: "100%", height: "120px", border: "1px solid #BBBBBB", padding: "15px" }}>{product.description}</p>
            </div>
            <div style={{ width: "50%", borderRight: "0.5px solid #C0C0C0" }}>
                <h2 className="text text-center">Thông tin chuyến đi</h2>
                <div style={{ display: "flex", alignItems: "center" }}>
                    <img width="30px" src="https://img.lovepik.com/element/40137/9272.png_1200.png" />
                    <p style={{ marginTop: "15px", marginLeft: "5px", fontWeight: "600" }}>Giá</p>
                </div>
                
                <ul style={{ fontWeight: "600" }}>
                    <li>
                        Người lớn: {" "}
                        <span style={{ marginLeft: "10px" }}>{formatPriceAdult}</span>
                    </li>
                    <li>
                        Người lớn: {" "}
                        <span style={{ marginLeft: "10px" }}>{formatPriceChild}</span>
                    </li>
                </ul>
                <div style={{ display: "flex", alignItems: "center" }}>
                    <img width="30px" src="https://elitetour.com.vn/Content/img/tour/s2.png" />
                    <p style={{ marginTop: "15px", marginLeft: "5px", fontWeight: "600" }}>
                        Phương tiện: <span style={{ marginLeft: "10px" }}>{product.vehicle}</span>
                    </p>
                </div>
                <div style={{ display: "flex", alignItems: "center", fontWeight: "600" }}>
                    <img width="30px" src="https://elitetour.com.vn/Content/img/tour/s3.png" />
                    <p style={{ marginTop: "15px", marginLeft: "5px" }}>
                        Chỗ ở: <span style={{ marginLeft: "10px" }}>{product.accommodation}</span>
                    </p>

                </div>
                <div style={{ display: "flex", alignItems: "center", fontWeight: "600" }}>
                    <img width="30px" src="https://banner2.cleanpng.com/20180404/hbw/kisspng-computer-icons-time-clock-time-5ac4f18f997ce1.4548585415228563356287.jpg" />
                    <p style={{ marginTop: "15px", marginLeft: "5px" }}>
                        Thời gian: <span style={{ marginLeft: "10px" }}>{product.time}</span>
                    </p>
                </div>
                <p style={{ fontWeight: "600" }}>
                    Loại Tours: <span style={{ marginLeft: "10px" }}>{product.tourCate.categoryName}</span>
                </p>
                <p style={{ fontWeight: "600" }}>Đánh giá trải nghiệm:</p>
                <div>
                    {starValues.map((value) => (
                        <span
                            key={value}
                            onClick={() => handleStarClick(value)}
                            style={{
                                cursor: "pointer",
                                fontSize: "24px",
                                color: value <= ratingStar ? "gold" : "gray",
                            }}
                        >
                             &#9733;
                        </span>
                    ))}   
                </div>
                <button className="btn btn-info" style={{width:"25%"}} onClick={handleSubmitRating}>Gửi Đánh Giá</button>
            </div>
            <div style={{ float: "right", marginTop: "-498px", width: "50%", textAlign: "center" }}>
                <h2>Bình luận</h2>
                {comments.map(c =>
                    <div style={{ display: "flex", marginLeft: "10px" }}>
                        <img
                            width={"40px"}
                            height={"40px"}
                            src={c.userId.avatar}
                            className="rounded-circle small-avatar"
                            alt="Avatar"
                        />
                        <div>
                            <div style={{ marginLeft: "10px", borderRadius: "20px", padding: "7px", backgroundColor: "#F2F3F5" }}>
                                <p style={{ fontWeight: "bolder", marginBottom: "-3px" }}>{c.userId.username}</p>
                                <p style={{ marginBottom: "-3px" }}>{c.content}</p>
                            </div>
                            <Moment style={{ fontSize: "12px" }} locale="vi" fromNow>{c.createdDate}</Moment>
                        </div>
                    </div>
                )}
                {user === null ? <p>Vui lòng <Link to={url2}>đăng nhập</Link> để bình luận! </p> : <>
                    <div style={{ display: "flex", justifyContent: "flex-end", position: "absolute", bottom: "0", right: "0" }}>
                        <Button onClick={addComment} style={{ width: "30%", borderRadius: "10px", fontSize: "12px" }} className="mt-2" variant="info">Bình luận</Button>
                        <Form.Control value={content} onChange={e => setContent(e.target.value)} style={{ width: "488px" }} as="textarea" aria-label="With textarea" placeholder="Nội dung bình luận" />
                    </div>
                </>}
            </div>
        </Container>

    )
}
export default Details;