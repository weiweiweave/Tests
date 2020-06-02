//
//  DetailViewController.swift
//  RoomBooking
//
//  Created by Weiping Tok on 1/6/20.
//  Copyright © 2020 Weiping Tok. All rights reserved.
//

import UIKit
import WebKit

class DetailViewController: UIViewController, WKUIDelegate {
    
    @IBOutlet weak var webView: WKWebView!
    
    var qrData: QRData?
    
    override func loadView() {
        navigationItem.hidesBackButton = true
        
        let webConfiguration = WKWebViewConfiguration()
        webView = WKWebView(frame: .zero, configuration: webConfiguration)
        webView.uiDelegate = self
        view = webView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //print("qrdata: \(qrData?.codeString)")
        
        if let url = URL(string: qrData?.codeString ?? "") {
            let request = URLRequest(url: url)
            webView.load(request)
        }
        
    }
    
    
    @IBAction func backToHomePressed(_ sender: UIBarButtonItem) {
        _ = navigationController?.popToRootViewController(animated: true)
    }
    
    
    
}
