import { useEffect, useState } from "react";
import Apis, { endpoints } from "../../configs/Apis";
import MySpinner from "../../layout/MySpinner";
import { Button, Card, Col, Form, Row } from "react-bootstrap";
import './Tours.css';
import { Link, useNavigate, useSearchParams } from "react-router-dom";




const Tours = () => {
    const [products, setProducts] = useState(null);
    const [kw, setKw] = useState("")
    const [priceMin, setPriceMin] = useState("");
    const [priceMax, setPriceMax] = useState("");
    const [timeStart, setTimeStart] = useState("");
    const [timeEnd, setTimeEnd] = useState("");
    const nav = useNavigate();
    const [q] = useSearchParams();

    useEffect(() => {
        const loadProducts = async () => {
            try {
                let e = endpoints['products'];
                let cateId = q.get("cateId");
                if (cateId !== null)
                    e = `${e}?cateId=${cateId}`;
                else {
                    let kw = q.get("kw");
                    if (kw !== null)
                        e = `${e}?kw=${kw}`;
                }
                if (priceMin !== "" && priceMax !== "") {
                    e = `${e}&fromPrice=${priceMin}&toPrice=${priceMax}`;
                }
                console.log("ABC" + timeStart)
                if(timeStart !== "" && timeEnd !== ""){
                    const formattedStartDate = formatISODate(timeStart);
                    const formattedEndDate = formatISODate(timeEnd);
                    e = `${e}?kw=&timeStart=${formattedStartDate}&timeEnd=${formattedEndDate}`;
                }
                let res = await Apis.get(e);
                setProducts(res.data);
                console.log(res.data)
            } catch (error) {
                console.error(error)
            }
        }
        loadProducts();
    }, [q])
    
    function formatISODate(dateString) {
        if (!dateString) return '';
        const date = new Date(dateString);
        return date.toISOString();
    }
    
    const search = (evt) => {
        evt.preventDefault();
        const query = `?kw=${kw}&fromPrice=${priceMin}&toPrice=${priceMax}`;
        nav(`/tours/${query}`);
    }

    const searchDate = (evt) => {
        evt.preventDefault();
        const formattedStartDate = new Date(timeStart).toISOString();
        const formattedEndDate = new Date(timeEnd).toISOString();
        const query = `?timeStart=${formattedStartDate}&timeEnd=${formattedEndDate}`;
        console.log("formattedStartDate:", formattedStartDate);
        console.log("formattedEndDate:", formattedEndDate);
        nav(`/tours/${query}`);
    }


    if (products === null)
        return <MySpinner />
    if (products.length === 0)
        return <div className="text text-center">Không tìm thấy tours phù hợp.</div>;
    return (
        <>
            <Form onSubmit={search} style={{ marginTop: "-30px" }}>
                <Row style={{ display: "flex", justifyContent: "center" }}>
                    <Col xs="auto" style={{ width: "25%", marginRight: "-100px", marginTop: "15px" }} >
                        <Form.Control
                            type="number"
                            value={priceMin}
                            onChange={e => setPriceMin(e.target.value)}
                            placeholder="Giá tối thiểu"
                            className="mr-sm-2"
                        />
                    </Col>
                    <Col xs="auto" style={{ width: "25%", marginRight: "-100px", marginTop: "15px" }}>
                        <Form.Control
                            type="number"
                            value={priceMax}
                            onChange={e => setPriceMax(e.target.value)}
                            placeholder="Giá tối đa"
                            className="mr-sm-2"
                        />
                    </Col>
                    <Col xs="auto" style={{ width: "35%" }}>
                        <Form.Control
                            type="text"
                            value={kw}
                            onChange={e => setKw(e.target.value)}
                            placeholder="Nhập để tìm kiếm..."
                            className=" mr-sm-2"
                        />
                    </Col>
                    <Col xs="auto" style={{ marginTop: "45px", marginLeft: "130px" }}>
                        <Button type="submit">Tìm kiếm</Button>
                    </Col>
                </Row>
            </Form>

            <Form onSubmit={searchDate} style={{ display: "flex", marginTop: "-30px" }}>
                <Col xs="auto" style={{ width: "25%", marginRight: "-100px" }}>
                    <Form.Control
                        type="date"
                        value={timeStart}
                        onChange={e => setTimeStart(e.target.value)}
                        placeholder="Từ ngày"
                        className="mr-sm-2"
                        style={{ marginLeft: "560px" }}
                    />
                </Col>
                <Col xs="auto" style={{ width: "25%" }}>
                    <Form.Control
                        type="date"
                        value={timeEnd}
                        onChange={e => setTimeEnd(e.target.value)}
                        placeholder="Đến ngày"
                        className="mr-sm-2"
                        style={{ marginLeft: "580px" }}
                    />
                </Col>
                <Col xs="auto" style={{ marginTop: "33px", marginLeft: "500px" }}>
                    <Button type="submit">Tìm theo ngày</Button>
                </Col>
            </Form>
            <Row>
                {products.map(p => {
                    let url = `/tours/${p.id}`;
                    const price = p.priceId.priceAdult;
                    const formattedPrice = price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                    const timestamp = p.timeStart;
                    const options = {
                        year: 'numeric',
                        month: '2-digit',
                        day: '2-digit',
                        hour: 'numeric',
                        minute: 'numeric',
                        second: 'numeric',
                        timeZone: 'Asia/Ho_Chi_Minh'
                    };
                    const formattedTime = new Intl.DateTimeFormat('vi-VN', options).format(new Date(timestamp));
                    return <Col xs={12} md={3} lg={4}>
                        <Card id="card" style={{ width: '25rem', marginBottom: "15px" }}>
                            <Card.Img variant="top" src={p.image} width={"398px"} height={"265px"} />
                            <Card.Body>
                                <Card.Title>{p.name}</Card.Title>
                                <Card.Text>
                                    {"Thời gian:"} {p.time}
                                </Card.Text>
                                <Card.Text>
                                    {"Giá Tours:"} {formattedPrice}
                                </Card.Text>
                                <Card.Text>
                                    {"Khởi hành:"} {formattedTime}
                                </Card.Text>
                                <Link className="m-1 btn btn-info" variant="info" to={url}>Chi tiết Tours</Link>
                            </Card.Body>
                        </Card>
                    </Col>
                })}
            </Row>
        </>
    )
}

export default Tours;