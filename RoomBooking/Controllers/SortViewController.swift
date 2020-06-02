//
//  SortViewController.swift
//  RoomBooking
//
//  Created by Weiping Tok on 31/5/20.
//  Copyright © 2020 Weiping Tok. All rights reserved.
//

import UIKit

protocol SortViewControllerDelegate : NSObjectProtocol {
    func didSortRooms(_ sortViewController: SortViewController)
}

class SortViewController: UIViewController {
    
    var meetingRooms = [RoomModel]()
    
    var selectedTime: String = "08:00"
    
    weak var delegate: SortViewControllerDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        //print("SortViewController viewDidLoad")
        
    }
    
    @IBAction func locationPressed(_ sender: UIButton) {
        
        meetingRooms.sort(by: { $0.roomLevel < $1.roomLevel })
        
        delegate?.didSortRooms(self)
        
        _ = navigationController?.popViewController(animated: true)
    }
    
    @IBAction func capacityPressed(_ sender: UIButton) {
        
        meetingRooms.sort(by: { $0.roomCapacity < $1.roomCapacity })
        
        delegate?.didSortRooms(self)
        
        _ = navigationController?.popViewController(animated: true)
    }    
    
    @IBAction func availabilityPressed(_ sender: UIButton) {
        
        meetingRooms.sort(by: { $0.roomAvailability[selectedTime]! > $1.roomAvailability[selectedTime]! })
        
        delegate?.didSortRooms(self)
        
        _ = navigationController?.popViewController(animated: true)
    }
    
    
    
    

    func back(sender: UIBarButtonItem) {
        // Perform your custom actions
        // ...
        // Go back to the previous ViewController
        _ = navigationController?.popViewController(animated: true)
        
    }

}
