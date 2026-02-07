import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { motion, AnimatePresence } from 'framer-motion';

const API_URL = 'http://localhost:8080/api';

function Dashboard() {
    const navigate = useNavigate();
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    const [showLogoutConfirm, setShowLogoutConfirm] = useState(false);

    useEffect(() => {
        const userId = localStorage.getItem('userId');

        if (!userId) {
            navigate('/login');
            return;
        }

        fetchUserData(userId);
    }, [navigate]);

    const fetchUserData = async (userId) => {
        try {
            const response = await fetch(`${API_URL}/user/me`, {
                headers: {
                    'X-User-Id': userId,
                },
            });

            if (!response.ok) {
                throw new Error('Failed to fetch user data');
            }

            const data = await response.json();
            setUser(data);
        } catch (err) {
            console.error('Error fetching user:', err);
            localStorage.removeItem('userId');
            navigate('/login');
        } finally {
            setLoading(false);
        }
    };

    const handleLogoutClick = () => {
        setShowLogoutConfirm(true);
    };

    const handleLogoutConfirm = () => {
        localStorage.removeItem('userId');
        navigate('/login');
    };

    const handleLogoutCancel = () => {
        setShowLogoutConfirm(false);
    };

    if (loading) {
        return (
            <div className="loading">
                Loading
            </div>
        );
    }

    return (
        <div className="app-container">
            <div className="dashboard-container">
                <motion.header
                    className="dashboard-header"
                    initial={{ opacity: 0, y: -20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.5 }}
                >
                    <h1>
                        <span>Beta</span>Key Portal
                    </h1>
                    <motion.button
                        className="btn-logout"
                        onClick={handleLogoutClick}
                        whileHover={{ scale: 1.05 }}
                        whileTap={{ scale: 0.95 }}
                    >
                        Logout
                    </motion.button>
                </motion.header>

                <motion.div
                    className="profile-card"
                    initial={{ opacity: 0, y: 30 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ delay: 0.2, duration: 0.6 }}
                >
                    <motion.div
                        className="access-badge"
                        initial={{ opacity: 0, scale: 0.8 }}
                        animate={{ opacity: 1, scale: 1 }}
                        transition={{ delay: 0.4, duration: 0.4 }}
                    >
                        Beta Access Granted
                    </motion.div>

                    <div className="profile-info">
                        {/* Beta Key - Large and Prominent */}
                        <motion.div
                            className="beta-key-section"
                            initial={{ opacity: 0, scale: 0.9 }}
                            animate={{ opacity: 1, scale: 1 }}
                            transition={{ delay: 0.5, duration: 0.5 }}
                        >
                            <span className="info-label">Your Beta Key</span>
                            <div className="beta-key-display">
                                {user?.betaKey || 'XXXXX-XXXXX-XXXXX'}
                            </div>
                        </motion.div>

                        <motion.div
                            className="info-row"
                            initial={{ opacity: 0, x: -20 }}
                            animate={{ opacity: 1, x: 0 }}
                            transition={{ delay: 0.6, duration: 0.4 }}
                        >
                            <span className="info-label">Username</span>
                            <span className="info-value">{user?.username || 'N/A'}</span>
                        </motion.div>

                        <motion.div
                            className="info-row"
                            initial={{ opacity: 0, x: -20 }}
                            animate={{ opacity: 1, x: 0 }}
                            transition={{ delay: 0.7, duration: 0.4 }}
                        >
                            <span className="info-label">Email</span>
                            <span className="info-value">{user?.email || 'N/A'}</span>
                        </motion.div>

                        <motion.div
                            className="info-row"
                            initial={{ opacity: 0, x: -20 }}
                            animate={{ opacity: 1, x: 0 }}
                            transition={{ delay: 0.8, duration: 0.4 }}
                        >
                            <span className="info-label">Access Level</span>
                            <span className="role-badge">{user?.role || 'TESTER'}</span>
                        </motion.div>
                    </div>
                </motion.div>
            </div>

            {/* Logout Confirmation Modal */}
            <AnimatePresence>
                {showLogoutConfirm && (
                    <motion.div
                        className="modal-overlay"
                        initial={{ opacity: 0 }}
                        animate={{ opacity: 1 }}
                        exit={{ opacity: 0 }}
                        onClick={handleLogoutCancel}
                    >
                        <motion.div
                            className="modal-content"
                            initial={{ opacity: 0, scale: 0.8, y: 20 }}
                            animate={{ opacity: 1, scale: 1, y: 0 }}
                            exit={{ opacity: 0, scale: 0.8, y: 20 }}
                            transition={{ duration: 0.3 }}
                            onClick={(e) => e.stopPropagation()}
                        >
                            <h2>Confirm Logout</h2>
                            <p>Are you sure you want to leave the portal?</p>
                            <div className="modal-buttons">
                                <motion.button
                                    className="btn-cancel"
                                    onClick={handleLogoutCancel}
                                    whileHover={{ scale: 1.05 }}
                                    whileTap={{ scale: 0.95 }}
                                >
                                    Cancel
                                </motion.button>
                                <motion.button
                                    className="btn-confirm"
                                    onClick={handleLogoutConfirm}
                                    whileHover={{ scale: 1.05 }}
                                    whileTap={{ scale: 0.95 }}
                                >
                                    Logout
                                </motion.button>
                            </div>
                        </motion.div>
                    </motion.div>
                )}
            </AnimatePresence>
        </div>
    );
}

export default Dashboard;
