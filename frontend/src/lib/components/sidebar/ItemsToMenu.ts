import House from 'lucide-svelte/icons/house';
import User from 'lucide-svelte/icons/user';
import MailPlus from 'lucide-svelte/icons/mail-plus';
import Settings from 'lucide-svelte/icons/settings';

export const items = [
	{
		title: 'Home',
		url: '/dashboard',
		icon: House
	},
	{
		title: 'Users',
		url: '/dashboard/users',
		icon: User
	},
	{
		title: 'Settings',
		url: '/dashboard/settings',
		icon: Settings
	},
	{
		title: 'Invites',
		url: '/dashboard/invites',
		icon: MailPlus
	}
];
