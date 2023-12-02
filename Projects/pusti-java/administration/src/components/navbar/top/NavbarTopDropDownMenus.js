import React, { useContext } from 'react';
import { Dropdown } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import {
  dashboardRoutes,
  modulesRoutes
  // appRoutes,
  // pagesRoutes
} from 'routes/siteMaps';
import NavbarDropdown from './NavbarDropdown';
// import NavbarDropdownApp from './NavbarDropdownApp';
import AppContext from 'context/Context';
import NavbarDropdownModules from './NavbarDropdownModules';

const NavbarTopDropDownMenus = () => {
  const {
    config: { navbarCollapsed, showBurgerMenu },
    setConfig
  } = useContext(AppContext);

  const handleDropdownItemClick = () => {
    if (navbarCollapsed) {
      setConfig('navbarCollapsed', !navbarCollapsed);
    }
    if (showBurgerMenu) {
      setConfig('showBurgerMenu', !showBurgerMenu);
    }
  };
  return (
    <>
      <NavbarDropdown title="dashboard">
        {dashboardRoutes.children[0].children.map(route => (
          <Dropdown.Item
            key={route.name}
            as={Link}
            className={route.active ? 'link-600' : 'text-500'}
            to={route.to}
            onClick={handleDropdownItemClick}
          >
            {route.name}
          </Dropdown.Item>
        ))}
      </NavbarDropdown>

      {/* <NavbarDropdown title="app">
        <NavbarDropdownApp items={appRoutes.children} />
      </NavbarDropdown> */}

      {/* <NavbarDropdown title="pages">
        <NavbarDropdownPages items={pagesRoutes.children} />
      </NavbarDropdown> */}
      <NavbarDropdown title="modules">
        <NavbarDropdownModules items={modulesRoutes.children} />
      </NavbarDropdown>

      {/* <NavbarDropdown title="documentation">
        {flatRoutes(documentationRoutes.children).map(route => (
          <Dropdown.Item
            key={route.name}
            as={Link}
            className={route.active ? 'link-600' : 'text-500'}
            to={route.to}
            onClick={handleDropdownItemClick}
          >
            {route.name}
          </Dropdown.Item>
        ))}
      </NavbarDropdown> */}
    </>
  );
};

export default NavbarTopDropDownMenus;
