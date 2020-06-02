//
//  RoomModel.swift
//  RoomBooking
//
//  Created by Weiping Tok on 30/5/20.
//  Copyright © 2020 Weiping Tok. All rights reserved.
//

import Foundation

struct RoomModel {
    let roomName: String
    let roomCapacity: Int
    let roomLevel: Int
    let roomAvailability: [String: Int]
}
