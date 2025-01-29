/*---------------------------------------------------\
|  Copyright (©) 2K25 EPN-FIS. All rights reserved.  |
|  andres.veas@epn.edu.ec  PROPRIETARY/CONFIDENTIAL. |
|  Use is subject to license terms.     andres.veas  |
\---------------------------------------------------*/

import BusinessLogic.BLFactory;
import DataAccess.DAO.LibroDAO;
import DataAccess.DTO.LibroDTO;

public class App {
    public static void main(String[] args) throws Exception {
        
        BLFactory <LibroDTO> f = new BLFactory<>(LibroDAO :: new);

        for (LibroDTO a : f.getAll()) {
            System.out.println(a);
        }
    }
}
