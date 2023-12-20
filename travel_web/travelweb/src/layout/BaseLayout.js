import { Container } from "react-bootstrap";
import Header from "./Header";
import Footer from "./Footer";

function BaseLayout({ children }) {
    return (
      <div className="">
        <Header />
        <Container>
          <div className="">{children}</div>
        </Container>
        <Footer />
      </div>
    );
  }

  export default BaseLayout;