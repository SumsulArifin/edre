import WizardAuth from 'components/authentication/wizard/WizardAuth';
import Landing from 'components/pages/landing/Landing';
import React from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import AuthSimpleLayout from '../layouts/AuthSimpleLayout';
import ErrorLayout from '../layouts/ErrorLayout';
import MainLayout from '../layouts/MainLayout';
import Calendar from 'components/app/calendar/Calendar';
import Chat from 'components/app/chat/Chat';
import Invoice from 'components/app/e-commerce/Invoice';
import Billing from 'components/app/e-commerce/billing/Billing';
import ShoppingCart from 'components/app/e-commerce/cart/ShoppingCart';
import Checkout from 'components/app/e-commerce/checkout/Checkout';
import CustomersDetails from 'components/app/e-commerce/customers-details/CustomersDetails';
import Customers from 'components/app/e-commerce/customers/Customers';
import OrderDetails from 'components/app/e-commerce/orders/order-details/OrderDetails';
import Orders from 'components/app/e-commerce/orders/order-list/Orders';
import Products from 'components/app/e-commerce/product/Products';
import ProductDetails from 'components/app/e-commerce/product/product-details/ProductDetails';
import Courses from 'components/app/e-learning/course/Courses';
import CourseDetails from 'components/app/e-learning/course/course-details';
import CreateCourse from 'components/app/e-learning/course/create-a-course';
import StudentOverview from 'components/app/e-learning/student-overview';
import TrainerProfile from 'components/app/e-learning/trainer-profile';
import Compose from 'components/app/email/compose/Compose';
import EmailDetail from 'components/app/email/email-detail/EmailDetail';
import Inbox from 'components/app/email/inbox/Inbox';
import CreateEvent from 'components/app/events/create-an-event/CreateEvent';
import EventDetail from 'components/app/events/event-detail/EventDetail';
import EventList from 'components/app/events/event-list/EventList';
import Kanban from 'components/app/kanban/Kanban';
import ActivityLog from 'components/app/social/activity-log/ActivityLog';
import Feed from 'components/app/social/feed/Feed';
import Followers from 'components/app/social/followers/Followers';
import Notifications from 'components/app/social/notifications/Notifications';
import ModalAuth from 'components/authentication/modal/ModalAuth';
import Analytics from 'components/dashboards/analytics';
import Crm from 'components/dashboards/crm';
import Ecommerce from 'components/dashboards/e-commerce';
import Lms from 'components/dashboards/lms';
import ProjectManagement from 'components/dashboards/project-management';
import Saas from 'components/dashboards/saas';
import Accordion from 'components/doc-components/Accordion';
import AdvanceSelect from 'components/doc-components/AdvanceSelect';
import AdvanceTableExamples from 'components/doc-components/AdvanceTableExamples';
import Alerts from 'components/doc-components/Alerts';
import AnimatedIcons from 'components/doc-components/AnimatedIcons';
import Avatar from 'components/doc-components/Avatar';
import Background from 'components/doc-components/Backgrounds';
import Badges from 'components/doc-components/Badges';
import BootstrapCarousel from 'components/doc-components/BootstrapCarousel';
import Breadcrumbs from 'components/doc-components/Breadcrumb';
import Buttons from 'components/doc-components/Buttons';
import CalendarExample from 'components/doc-components/CalendarExample';
import Cards from 'components/doc-components/Cards';
import Checks from 'components/doc-components/Checks';
import Collapse from 'components/doc-components/Collapse';
import ComboNavbar from 'components/doc-components/ComboNavbar';
import CookieNoticeExample from 'components/doc-components/CookieNoticeExample';
import CountUp from 'components/doc-components/CountUp';
import DatePicker from 'components/doc-components/DatePicker';
import DraggableExample from 'components/doc-components/DraggableExample';
import Dropdowns from 'components/doc-components/Dropdowns';
import Editor from 'components/doc-components/Editor';
import Embed from 'components/doc-components/Embed';
import EmojiMartExample from 'components/doc-components/EmojiMart';
import Figures from 'components/doc-components/Figures';
import FileUploader from 'components/doc-components/FileUploader';
import FloatingLabels from 'components/doc-components/FloatingLabels';
import FontAwesome from 'components/doc-components/FontAwesome';
import FormControl from 'components/doc-components/FormControl';
import FormLayout from 'components/doc-components/FormLayout';
import FormValidation from 'components/doc-components/FormValidation';
import GoogleMapExample from 'components/doc-components/GoogleMapExample';
import Hoverbox from 'components/doc-components/Hoverbox';
import Image from 'components/doc-components/Image';
import InputGroup from 'components/doc-components/InputGroup';
import LeafletMapExample from 'components/doc-components/LeafletMapExample';
import Lightbox from 'components/doc-components/Lightbox';
import ListGroups from 'components/doc-components/ListGroups';
import Modals from 'components/doc-components/Modals';
import NavBarTop from 'components/doc-components/NavBarTop';
import NavbarDoubleTop from 'components/doc-components/NavbarDoubleTop';
import Navbars from 'components/doc-components/Navbars';
import Navs from 'components/doc-components/Navs';
import Offcanvas from 'components/doc-components/Offcanvas';
import Pagination from 'components/doc-components/Pagination';
import Placeholder from 'components/doc-components/Placeholder';
import Popovers from 'components/doc-components/Popovers';
import BasicProgressBar from 'components/doc-components/ProgressBar';
import Range from 'components/doc-components/Range';
import Rating from 'components/doc-components/Rating';
import ReactIcons from 'components/doc-components/ReactIcons';
import ReactPlayerExample from 'components/doc-components/ReactPlayerExample';
import Scrollbar from 'components/doc-components/Scrollbar';
import Scrollspy from 'components/doc-components/Scrollspy';
import Search from 'components/doc-components/Search';
import Select from 'components/doc-components/Select';
import SlickCarousel from 'components/doc-components/SlickCarousel';
import Spinners from 'components/doc-components/Spinners';
import Tables from 'components/doc-components/Tables';
import Tabs from 'components/doc-components/Tabs';
import Timeline from 'components/doc-components/Timeline';
import Toasts from 'components/doc-components/Toasts';
import Tooltips from 'components/doc-components/Tooltips';
import TreeviewExample from 'components/doc-components/TreeviewExample';
import TypedText from 'components/doc-components/TypedText';
import VerticalNavbar from 'components/doc-components/VerticalNavbar';
import WizardForms from 'components/doc-components/WizardForms';
import Chartjs from 'components/doc-components/charts-example/chartjs';
import D3js from 'components/doc-components/charts-example/d3';
import HowToUse from 'components/doc-components/charts-example/echarts/HowToUse';
import BarCharts from 'components/doc-components/charts-example/echarts/bar-charts';
import CandlestickCharts from 'components/doc-components/charts-example/echarts/candlestick-charts';
import GeoMaps from 'components/doc-components/charts-example/echarts/geo-map';
import HeatmapCharts from 'components/doc-components/charts-example/echarts/heatmap-chart';
import LineCharts from 'components/doc-components/charts-example/echarts/line-charts';
import PieCharts from 'components/doc-components/charts-example/echarts/pie-charts';
import RadarCharts from 'components/doc-components/charts-example/echarts/radar-charts/Index';
import ScatterCharts from 'components/doc-components/charts-example/echarts/scatter-charts';
import Configuration from 'components/documentation/Configuration';
import DarkMode from 'components/documentation/DarkMode';
import DesignFile from 'components/documentation/DesignFile';
import GettingStarted from 'components/documentation/GettingStarted';
import Plugins from 'components/documentation/Plugins';
import Styling from 'components/documentation/Styling';
import Changelog from 'components/documentation/change-log/ChangeLog';
// import Starter from 'components/pages/Starter';
import Associations from 'components/pages/asscociations/Associations';
import FaqAccordion from 'components/pages/faq/faq-accordion/FaqAccordion';
import FaqAlt from 'components/pages/faq/faq-alt/FaqAlt';
import FaqBasic from 'components/pages/faq/faq-basic/FaqBasic';
import InvitePeople from 'components/pages/miscellaneous/invite-people/InvitePeople';
import PrivacyPolicy from 'components/pages/miscellaneous/privacy-policy/PrivacyPolicy';
import PricingAlt from 'components/pages/pricing/pricing-alt/PricingAlt';
import PricingDefault from 'components/pages/pricing/pricing-default/PricingDefault';
import Profile from 'components/pages/user/profile/Profile';
import Settings from 'components/pages/user/settings/Settings';
import Borders from 'components/utilities/Borders';
import ColoredLinks from 'components/utilities/ColoredLinks';
import Colors from 'components/utilities/Colors';
import Display from 'components/utilities/Display';
import Flex from 'components/utilities/Flex';
import Float from 'components/utilities/Float';
import Grid from 'components/utilities/Grid';
import Position from 'components/utilities/Position';
import Sizing from 'components/utilities/Sizing';
import Spacing from 'components/utilities/Spacing';
import StretchedLink from 'components/utilities/StretchedLink';
import TextTruncation from 'components/utilities/TextTruncation';
import Typography from 'components/utilities/Typography';
import VerticalAlign from 'components/utilities/VerticalAlign';
import Visibility from 'components/utilities/Visibility';
import Widgets from 'widgets/Widgets';
import Error404 from 'components/errors/Error404';
import Error500 from 'components/errors/Error500';
import SimpleConfirmMail from 'components/authentication/simple/ConfirmMail';
import SimpleForgetPassword from 'components/authentication/simple/ForgetPassword';
import SimpleLockScreen from 'components/authentication/simple/LockScreen';
import SimpleLogin from 'components/authentication/simple/Login';
import SimpleLogout from 'components/authentication/simple/Logout';
import SimplePasswordReset from 'components/authentication/simple/PasswordReset';
import SimpleRegistration from 'components/authentication/simple/Registration';
import Logout from 'components/authentication/Logout';
import CardConfirmMail from 'components/authentication/card/ConfirmMail';
import CardForgetPassword from 'components/authentication/card/ForgetPassword';
import CardLockScreen from 'components/authentication/card/LockScreen';
import CardLogin from 'components/authentication/card/Login';
import CardPasswordReset from 'components/authentication/card/PasswordReset';
import CardRegistration from 'components/authentication/card/Registration';
import SplitConfirmMail from 'components/authentication/split/ConfirmMail';
import SplitForgetPassword from 'components/authentication/split/ForgetPassword';
import SplitLockScreen from 'components/authentication/split/LockScreen';
import SplitLogin from 'components/authentication/split/Login';
import SplitLogout from 'components/authentication/split/Logout';
import SplitPasswordReset from 'components/authentication/split/PasswordReset';
import SplitRegistration from 'components/authentication/split/Registration';
import Dashboard from 'components/dashboards/default';
import Faq from 'components/documentation/Faq';
import Wizard from 'components/wizard/Wizard';
import ContactDetails from 'components/app/support-desk/contact-details/ContactDetails';
import Contacts from 'components/app/support-desk/contacts/Contacts';
import QuickLinks from 'components/app/support-desk/quick-links/QuickLinks';
import Reports from 'components/app/support-desk/reports/Reports';
import CardView from 'components/app/support-desk/tickets-layout/CardView';
import TableView from 'components/app/support-desk/tickets-layout/TableView';
import TicketsPreview from 'components/app/support-desk/tickets-preview/TicketsPreview';
import SupportDesk from 'components/dashboards/support-desk';
import InputMaskExample from 'components/doc-components/InputMaskExample';
import RangeSlider from 'components/doc-components/RangeSlider';
import Areas from 'components/pages/areas';
import Banks from 'components/pages/banks';
import Distributors from 'components/pages/distributors';
import Divisions from 'components/pages/divisions/index';
import PustiRoutes from 'components/pages/pustiroutes';
import Regions from 'components/pages/regions';
import SalesOrgs from 'components/pages/salesorgs';
import Zones from 'components/pages/zones';
import PropTypes from 'prop-types';
import Brand from 'components/pages/product-setup/brand/index';
import National from 'components/pages/National/index';
import Manipulated from '../components/pages/manipulateDepos/index';
import SalesOfficer from 'components/pages/SalesOfficer';
import DivisionalHead from 'components/pages/DivisionalHead';
import RegionalHead from 'components/pages/RegionalHead';
import ZonalHead from 'components/pages/ZonalHead';
import FactoryMangement from '../components/pages/factoryManagement'
import Inventory from 'components/pages/Inventory';
import Category from '../components/pages/Category';
import CategoryType from 'components/pages/CategoryType';
import Outlets from 'components/pages/outlets/index';
import EmployeeManagement from 'components/pages/UserManagement/EmployeeManagement';
import Product from 'components/pages/Product';
import DistributorSalesResentative from 'components/pages/DistributorSalesResentative/index'
import OfficeNotice from 'components/pages/Notice/index'
import Employee from 'components/pages/Employee';
import AddEmployee from 'components/pages/addEmployee';
import TimePassForm from 'components/pages/TimePass/TimePassForm';
import PrimaryOrderProcess from 'components/pages/primaryOrderProcess/primaryOrderProcess';
import AddPrimaryOrderProcess from 'components/pages/primaryOrderProcess/AddPrimaryOrderProcess';
import RouteForm from 'components/pages/pustiroutes/RouteForm';
import ApprovalCommittee from 'components/pages/ApprovalCommittee/index';
import RouteMap from 'components/pages/pustiroutes/RouteMap';
import AddProduct from 'components/pages/Product/AddProduct';
import AddCategoryType from 'components/pages/CategoryType/AddCategoryType';
import OfferType from 'components/pages/OfferType/index';
import Add_OfferType from 'components/pages/OfferType/Add_OfferType';
import OutletMap from 'components/pages/outlets/OutletMap';
import DeliveryApproval from 'components/pages/DeliveryApproval';
import FactoryManagementForm from 'components/pages/factoryManagement/FactoryManagementForm';
import DistributorForm from 'components/pages/distributors/DistributorForm';
import DepotForm from 'components/pages/manipulateDepos/DepoForm';
import OutletForm from 'components/pages/outlets/OutletForm';
import NoticeForm from 'components/pages/Notice/NoticeForm';
import NationalForm from 'components/pages/National/nationalForm';
import DivisionForm from 'components/pages/divisions/divisionForm';
import RegionForm from 'components/pages/regions/regionForm';
import ZoneForm from 'components/pages/zones/zoneForm';
import InventoryForm from 'components/pages/Inventory/inventoryForm';
import BankForm from 'components/pages/banks/bankForm';
import BrandForm from 'components/pages/product-setup/brand/brandForm';
import SalesOrgForm from 'components/pages/salesorgs/salesorgForm';


const FalconRoutes = ({ isAuthenticated }) => {
  let routes = (
    <Routes>
      {/*- ------------- Card ---------------------------  */}
      <Route path="authentication/card/login" element={<CardLogin />} />

      <Route
        path="authentication/card/register"
        element={<CardRegistration />}
      />

      {/* <Route path='/' element={<CardLogin />}></Route> */}

      <Route
        path="*"
        element={<Navigate to="authentication/card/login" replace />}
      />



    </Routes>
  );

  if (isAuthenticated) {
    routes = (
      <Routes>
        <Route path="landing" element={<Landing />} />
        <Route element={<ErrorLayout />}>
          <Route path="errors/404" element={<Error404 />} />
          <Route path="errors/500" element={<Error500 />} />
        </Route>
        {/*- ------------- Authentication ---------------------------  */}

        {/*- ------------- simple ---------------------------  */}
        <Route element={<AuthSimpleLayout />}>
          <Route path="authentication/simple/login" element={<SimpleLogin />} />
          <Route
            path="authentication/simple/register"
            element={<SimpleRegistration />}
          />
          <Route
            path="authentication/simple/logout"
            element={<SimpleLogout />}
          />
          <Route
            path="authentication/simple/forgot-password"
            element={<SimpleForgetPassword />}
          />
          <Route
            path="authentication/simple/reset-password"
            element={<SimplePasswordReset />}
          />
          <Route
            path="authentication/simple/confirm-mail"
            element={<SimpleConfirmMail />}
          />
          <Route
            path="authentication/simple/lock-screen"
            element={<SimpleLockScreen />}
          />
        </Route>

        {/*- ------------- Card ---------------------------  */}
        <Route path="authentication/card/login" element={<CardLogin />} />
        <Route
          path="authentication/card/register"
          element={<CardRegistration />}
        />
        <Route path="authentication/card/logout" element={<Logout />} />
        <Route
          path="authentication/card/forgot-password"
          element={<CardForgetPassword />}
        />
        <Route
          path="authentication/card/reset-password"
          element={<CardPasswordReset />}
        />
        <Route
          path="authentication/card/confirm-mail"
          element={<CardConfirmMail />}
        />
        <Route
          path="authentication/card/lock-screen"
          element={<CardLockScreen />}
        />

        {/*- ------------- Split ---------------------------  */}

        <Route path="authentication/split/login" element={<SplitLogin />} />
        <Route path="authentication/split/logout" element={<SplitLogout />} />
        <Route
          path="authentication/split/register"
          element={<SplitRegistration />}
        />
        <Route
          path="authentication/split/forgot-password"
          element={<SplitForgetPassword />}
        />
        <Route
          path="authentication/split/reset-password"
          element={<SplitPasswordReset />}
        />
        <Route
          path="authentication/split/confirm-mail"
          element={<SplitConfirmMail />}
        />
        <Route
          path="authentication/split/lock-screen"
          element={<SplitLockScreen />}
        />

        <Route element={<WizardAuth />}>
          <Route
            path="authentication/wizard"
            element={<Wizard validation={true} />}
          />
        </Route>

        {/* //--- MainLayout Starts  */}

        <Route element={<MainLayout />}>
          {/*Dashboard*/}
          <Route path="/" element={<Dashboard />} />
          <Route path="dashboard/analytics" element={<Analytics />} />
          <Route path="dashboard/crm" element={<Crm />} />
          <Route path="dashboard/saas" element={<Saas />} />
          <Route path="dashboard/e-commerce" element={<Ecommerce />} />
          <Route path="dashboard/lms" element={<Lms />} />


          <Route
            path="dashboard/project-management"
            element={<ProjectManagement />}
          />
          <Route path="dashboard/support-desk" element={<SupportDesk />} />
          {/* E Commerce */}
          <Route
            path="e-commerce/orders/order-details"
            element={<OrderDetails />}
          />
          <Route path="e-commerce/orders/order-list" element={<Orders />} />
          <Route path="e-commerce/invoice" element={<Invoice />} />
          <Route path="e-commerce/billing" element={<Billing />} />
          <Route path="e-commerce/checkout" element={<Checkout />} />
          <Route path="e-commerce/shopping-cart" element={<ShoppingCart />} />
          <Route path="e-commerce/customers" element={<Customers />} />
          <Route path="master/division" element={<Divisions />} />
          <Route path='master/division/add/:updateID?' element={<DivisionForm />} />
          <Route path="master/zone" element={<Zones />} />
          <Route path='master/zone/add/:updateID?' element={<ZoneForm />} />
          <Route path="master/region" element={<Regions />} />
          <Route path='master/region/add/:updateID?' element={<RegionForm />} />
          <Route path="master/area" element={<Areas />} />
          <Route path="master/salesorg" element={<SalesOrgs />} />
          <Route path='master/salesorg/add/:updateID?' element={<SalesOrgForm />} />
          <Route path="master/salesOfficer" element={<SalesOfficer />}></Route>
          <Route path="master/national" element={<National />}></Route>
          <Route path='master/national/add/:updateID?' element={<NationalForm />} />
          <Route path="master/bank" element={<Banks />} />
          <Route path="master/bank/add/:updateID?" element={<BankForm />} />
          <Route path="master/brand" element={<Brand />}></Route>
          <Route path='master/brand/add/:updateID?' element={<BrandForm />} />
          <Route path="master/route" element={<PustiRoutes />} />
          <Route path='master/route/add/:updateID?' element={<RouteForm />} />
          <Route path='master/route/map' element={<RouteMap />} />
          <Route path="master/outlet" element={<Outlets />} />
          <Route path='master/outlet/add/:updateID?' element={<OutletForm />}></Route>
          <Route path='master/outlet/map' element={<OutletMap />} />
          <Route path="master/distributor" element={<Distributors />} />
          <Route path='master/distributor/add/:updateID?' element={<DistributorForm />} />
          <Route path="master/inventory" element={<Inventory />} />
          <Route path='master/inventory/add/:updateID?' element={<InventoryForm />} />
          <Route path="authentication/logout" element={<Logout />} />
          <Route path='organogram/divisionlhead' element={<DivisionalHead />} />
          <Route path='organogram/regionalhead' element={<RegionalHead />} />
          <Route path='organogram/zonalhead' element={<ZonalHead />} />
          <Route path='factoryManagements' element={<FactoryMangement />} />
          <Route path='factoryManagement/add/:updateID?' element={<FactoryManagementForm />}></Route>
          <Route path='factoryManagements' element={<FactoryMangement />} />
          <Route path='master/categoryType' element={<CategoryType />} />
          <Route path='master/categoryType/add/:updateID?' element={<AddCategoryType />} />
          <Route path='master/category' element={<Category />} />
          <Route path='usersManagement' element={<EmployeeManagement />} />
          <Route path='/master/approvalCommittee' element={<ApprovalCommittee />} />
          <Route path='master/product' element={<Product />} />
          <Route path='master/product/add/:updateID?' element={<AddProduct />} />
          <Route path='master/officenotice' element={<OfficeNotice />} />
          <Route path='master/officenotice/add/:updateID?' element={<NoticeForm />} />
          <Route path='master/distributorsalesrepresentative' element={<DistributorSalesResentative />} />
          <Route path='target/timePass/form/:updateID?' element={<TimePassForm />} />
          <Route path='master/employee' element={<Employee />} />
          <Route path='master/employee/add/:updateID?' element={<AddEmployee />} />
          <Route path='primaryOrderProcess' element={<PrimaryOrderProcess />} />
          <Route path='primaryOrderProcess/add/:updateID?' element={<AddPrimaryOrderProcess />} />
          <Route path='offerType' element={<OfferType />}></Route>
          <Route path='offerType/add/:updateID?' element={<Add_OfferType />}></Route>
          <Route path='deliveryApproval' element={<DeliveryApproval />}></Route>
          <Route path="manipulate" element={<Manipulated />} />
          <Route path='depot/add/:updateID?' element={<DepotForm />}></Route>

          <Route
            path="e-commerce/customer-details"
            element={<CustomersDetails />}
          />

          <Route
            path="e-commerce/product/product-details"
            element={<ProductDetails />}
          />
          <Route
            path="e-commerce/product/product-details/:productId"
            element={<ProductDetails />}
          />

          <Route
            path="e-commerce/product/:productLayout"
            element={<Products />}
          />

          <Route path="e-commerce/invoice" element={<Invoice />} />

          {/* E Learning */}
          <Route path="e-learning/course/:courseLayout" element={<Courses />} />
          <Route
            path="e-learning/course/course-details"
            element={<CourseDetails />}
          />
          <Route
            path="e-learning/course/course-details/:courseId"
            element={<CourseDetails />}
          />
          <Route
            path="e-learning/course/create-a-course"
            element={<CreateCourse />}
          />
          <Route
            path="e-learning/trainer-profile"
            element={<TrainerProfile />}
          />
          <Route
            path="e-learning/student-overview"
            element={<StudentOverview />}
          />

          {/*icons*/}
          <Route path="icons/font-awesome" element={<FontAwesome />} />
          <Route path="icons/react-icons" element={<ReactIcons />} />

          {/* maps */}
          <Route path="maps/google-map" element={<GoogleMapExample />} />
          <Route path="maps/leaflet-map" element={<LeafletMapExample />} />

          {/*App*/}
          <Route path="app/calendar" element={<Calendar />} />
          <Route path="app/chat" element={<Chat />} />
          <Route path="app/kanban" element={<Kanban />} />
          <Route path="social/feed" element={<Feed />} />
          <Route path="social/activity-log" element={<ActivityLog />} />
          <Route path="social/notifications" element={<Notifications />} />
          <Route path="social/followers" element={<Followers />} />
          <Route path="events/event-detail" element={<EventDetail />} />
          <Route path="events/create-an-event" element={<CreateEvent />} />
          <Route path="events/event-list" element={<EventList />} />

          {/* Email */}
          <Route path="email/email-detail" element={<EmailDetail />} />
          <Route path="email/inbox" element={<Inbox />} />
          <Route path="email/compose" element={<Compose />} />

          {/* support desk */}
          <Route path="/support-desk/table-view" element={<TableView />} />
          <Route path="/support-desk/card-view" element={<CardView />} />
          <Route path="/support-desk/contacts" element={<Contacts />} />
          <Route
            path="/support-desk/contact-details"
            element={<ContactDetails />}
          />
          <Route
            path="/support-desk/tickets-preview"
            element={<TicketsPreview />}
          />
          <Route path="/support-desk/quick-links" element={<QuickLinks />} />
          <Route path="/support-desk/reports" element={<Reports />} />

          {/*Pages*/}
          {/* <Route path="pages/starter" element={<Starter />} /> */}
          <Route path="user/profile" element={<Profile />} />
          <Route path="user/settings" element={<Settings />} />
          <Route path="miscellaneous/associations" element={<Associations />} />
          <Route path="faq/faq-alt" element={<FaqAlt />} />
          <Route path="faq/faq-basic" element={<FaqBasic />} />
          <Route path="faq/faq-accordion" element={<FaqAccordion />} />
          <Route path="pricing/pricing-default" element={<PricingDefault />} />
          <Route path="pricing/pricing-alt" element={<PricingAlt />} />
          <Route
            path="miscellaneous/privacy-policy"
            element={<PrivacyPolicy />}
          />
          <Route
            path="miscellaneous/invite-people"
            element={<InvitePeople />}
          />
          {/* charts-example */}

          <Route path="charts/chartjs" element={<Chartjs />} />
          <Route path="charts/d3js" element={<D3js />} />
          <Route path="charts/echarts/line-charts" element={<LineCharts />} />
          <Route path="charts/echarts/bar-charts" element={<BarCharts />} />
          <Route
            path="charts/echarts/candlestick-charts"
            element={<CandlestickCharts />}
          />
          <Route path="charts/echarts/geo-map" element={<GeoMaps />} />
          <Route
            path="charts/echarts/scatter-charts"
            element={<ScatterCharts />}
          />
          <Route path="charts/echarts/pie-charts" element={<PieCharts />} />
          <Route path="charts/echarts/radar-charts" element={<RadarCharts />} />
          <Route
            path="charts/echarts/heatmap-charts"
            element={<HeatmapCharts />}
          />
          <Route path="charts/echarts/how-to-use" element={<HowToUse />} />

          {/*Components*/}
          <Route path="components/alerts" element={<Alerts />} />
          <Route path="components/accordion" element={<Accordion />} />
          <Route path="components/animated-icons" element={<AnimatedIcons />} />
          <Route path="components/badges" element={<Badges />} />
          <Route path="components/breadcrumb" element={<Breadcrumbs />} />
          <Route path="components/buttons" element={<Buttons />} />
          <Route path="components/calendar" element={<CalendarExample />} />
          <Route path="components/cards" element={<Cards />} />
          <Route path="components/dropdowns" element={<Dropdowns />} />
          <Route path="components/list-group" element={<ListGroups />} />
          <Route path="components/modals" element={<Modals />} />
          <Route path="components/offcanvas" element={<Offcanvas />} />
          <Route path="components/pagination" element={<Pagination />} />
          <Route
            path="components/progress-bar"
            element={<BasicProgressBar />}
          />
          <Route path="components/placeholder" element={<Placeholder />} />
          <Route path="components/spinners" element={<Spinners />} />
          <Route path="components/toasts" element={<Toasts />} />
          <Route path="components/pictures/avatar" element={<Avatar />} />
          <Route path="components/pictures/images" element={<Image />} />
          <Route path="components/pictures/figures" element={<Figures />} />
          <Route path="components/pictures/hoverbox" element={<Hoverbox />} />
          <Route path="components/pictures/lightbox" element={<Lightbox />} />
          <Route path="components/tooltips" element={<Tooltips />} />
          <Route path="components/popovers" element={<Popovers />} />
          <Route path="components/draggable" element={<DraggableExample />} />
          <Route path="components/scrollspy" element={<Scrollspy />} />
          <Route path="components/timeline" element={<Timeline />} />
          <Route path="components/treeview" element={<TreeviewExample />} />
          <Route
            path="components/carousel/bootstrap"
            element={<BootstrapCarousel />}
          />
          <Route path="components/carousel/slick" element={<SlickCarousel />} />
          <Route path="components/navs-and-tabs/navs" element={<Navs />} />
          <Route path="tables/basic-tables" element={<Tables />} />
          <Route
            path="tables/advance-tables"
            element={<AdvanceTableExamples />}
          />
          <Route path="forms/basic/form-control" element={<FormControl />} />
          <Route path="forms/basic/input-group" element={<InputGroup />} />
          <Route path="forms/basic/select" element={<Select />} />
          <Route path="forms/basic/checks" element={<Checks />} />
          <Route path="forms/basic/range" element={<Range />} />
          <Route path="forms/basic/layout" element={<FormLayout />} />
          <Route path="forms/advance/date-picker" element={<DatePicker />} />
          <Route path="forms/advance/editor" element={<Editor />} />
          <Route
            path="forms/advance/emoji-button"
            element={<EmojiMartExample />}
          />
          <Route
            path="forms/advance/advance-select"
            element={<AdvanceSelect />}
          />
          <Route
            path="forms/advance/input-mask"
            element={<InputMaskExample />}
          />
          <Route path="forms/advance/range-slider" element={<RangeSlider />} />
          <Route
            path="forms/advance/file-uploader"
            element={<FileUploader />}
          />
          <Route path="forms/advance/rating" element={<Rating />} />
          <Route path="forms/floating-labels" element={<FloatingLabels />} />
          <Route path="forms/validation" element={<FormValidation />} />
          <Route path="forms/wizard" element={<WizardForms />} />
          <Route path="components/navs-and-tabs/navbar" element={<Navbars />} />
          <Route path="components/navs-and-tabs/tabs" element={<Tabs />} />
          <Route path="components/collapse" element={<Collapse />} />
          <Route
            path="components/cookie-notice"
            element={<CookieNoticeExample />}
          />
          <Route path="components/countup" element={<CountUp />} />
          <Route path="components/videos/embed" element={<Embed />} />
          <Route
            path="components/videos/react-player"
            element={<ReactPlayerExample />}
          />
          <Route path="components/background" element={<Background />} />
          <Route path="components/search" element={<Search />} />
          <Route path="components/typed-text" element={<TypedText />} />
          <Route
            path="components/navs-and-tabs/vertical-navbar"
            element={<VerticalNavbar />}
          />
          <Route
            path="components/navs-and-tabs/top-navbar"
            element={<NavBarTop />}
          />
          <Route
            path="components/navs-and-tabs/double-top-navbar"
            element={<NavbarDoubleTop />}
          />
          <Route
            path="components/navs-and-tabs/combo-navbar"
            element={<ComboNavbar />}
          />

          {/*Utilities*/}
          <Route path="utilities/borders" element={<Borders />} />
          <Route path="utilities/colors" element={<Colors />} />
          <Route path="utilities/colored-links" element={<ColoredLinks />} />
          <Route path="utilities/display" element={<Display />} />
          <Route path="utilities/visibility" element={<Visibility />} />
          <Route path="utilities/stretched-link" element={<StretchedLink />} />
          <Route path="utilities/stretched-link" element={<StretchedLink />} />
          <Route path="utilities/float" element={<Float />} />
          <Route path="utilities/position" element={<Position />} />
          <Route path="utilities/spacing" element={<Spacing />} />
          <Route path="utilities/sizing" element={<Sizing />} />
          <Route
            path="utilities/text-truncation"
            element={<TextTruncation />}
          />
          <Route path="utilities/typography" element={<Typography />} />
          <Route path="utilities/vertical-align" element={<VerticalAlign />} />
          <Route path="utilities/flex" element={<Flex />} />
          <Route path="utilities/grid" element={<Grid />} />
          <Route path="utilities/scroll-bar" element={<Scrollbar />} />

          <Route path="widgets" element={<Widgets />} />

          {/*Documentation*/}
          <Route
            path="documentation/getting-started"
            element={<GettingStarted />}
          />
          <Route
            path="documentation/configuration"
            element={<Configuration />}
          />
          <Route path="documentation/styling" element={<Styling />} />
          <Route path="documentation/dark-mode" element={<DarkMode />} />
          <Route path="documentation/plugin" element={<Plugins />} />
          <Route path="documentation/faq" element={<Faq />} />
          <Route path="documentation/design-file" element={<DesignFile />} />
          <Route path="changelog" element={<Changelog />} />
          <Route path="authentication-modal" element={<ModalAuth />} />
        </Route >

        {/* //--- MainLayout end  */}

        {/* <Navigate to="/errors/404" /> */}
        <Route path="*" element={<Navigate to="/errors/404" replace />} />
      </Routes >
    );
  }

  return routes;
};

FalconRoutes.propTypes = {
  isAuthenticated: PropTypes.bool
};

export default FalconRoutes;
