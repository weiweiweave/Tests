//
//  Item.swift
//  URL App
//
//  Created by Weiping Tok on 22/5/20.
//  Copyright © 2020 Weiping Tok. All rights reserved.
//

import Foundation
import UIKit

struct ObjectModel {
    //property
    var icon = UIImage()
    var title: String = ""
    let url: String
    var isSelected: Bool = false
    
    //create initialiser
    init(url: String) {
        
        self.url = url
    }
}
